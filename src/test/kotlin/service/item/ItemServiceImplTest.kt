package service.item

import EntityFactory
import com.nhaarman.mockito_kotlin.*
import model.base.BaseWebserviceResponse
import model.base.WSCode
import model.entity.Item
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.springframework.http.HttpStatus
import repository.ItemRepository
import repository.LoggedUserRepository
import repository.UserRepository
import service.item.ItemServiceResponses.badRequestItemDoesNotExists
import service.item.ItemServiceResponses.badRequestItemForAnotherCompany
import service.item.ItemServiceResponses.badRequestUserTriesToGetItemsFromAnotherCompany
import service.item.ItemServiceResponses.successCompanyItem
import utils.WSString
import utils.converter.RequestConverter
import workflow.response.AddItemWebserviceResponse
import workflow.response.DeleteItemWebserviceResponse
import workflow.response.ItemWebserviceResponse
import workflow.response.ItemsWebserviceResponse

class ItemServiceImplTest {

    lateinit var systemUnderTest: ItemServiceImpl
    private val mockOfItemsRepository: ItemRepository = mock()
    private val mockOfLoggedUserRepository: LoggedUserRepository = mock()
    private val mockOfUserRepository: UserRepository = mock()
    private val mockOfConverter: RequestConverter = mock()

    private val authToken = "TOKEN"

    @Before
    fun setUp() {
        systemUnderTest = ItemServiceImpl(mockOfItemsRepository,
                mockOfLoggedUserRepository,
                mockOfUserRepository,
                mockOfConverter)

        whenever(mockOfUserRepository.findOne(any())).thenReturn(EntityFactory.user)
        whenever(mockOfLoggedUserRepository.findLoggedUserByAuthToken(any())).thenReturn(EntityFactory.loggedUser("TEST"))
    }

    @Test
    fun shouldNotAddNewItemWhenItOccursInDBAlready() {
        whenever(mockOfConverter.convertAddItemRequestToEntity(any())).thenReturn(EntityFactory.item)
        whenever(mockOfItemsRepository.findItemByToken(any())).thenReturn(EntityFactory.item)

        val expectedResponse = AddItemWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM, WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM.code, WSString.USER_ITEM_EXISTS.tag)

        val response = systemUnderTest.addItem("", EntityFactory.addItemRequest)

        responsesAreEqual(response, expectedResponse)
    }

    @Test
    fun shouldAddNewItem() {
        whenever(mockOfConverter.convertAddItemRequestToEntity(any())).thenReturn(EntityFactory.item)
        val expectedUser = EntityFactory.item
        val expectedResponse = AddItemWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "")

        val response = systemUnderTest.addItem("", EntityFactory.addItemRequest)

        responsesAreEqual(response, expectedResponse)

        val argument = ArgumentCaptor.forClass(Item::class.java)
        verify(mockOfItemsRepository).save(argument.capture())

        itemsAreEqual(argument.firstValue, expectedUser)
    }

    @Test
    fun shouldDeleteItem() {
        whenever(mockOfItemsRepository.findItemByToken("itemToken")).thenReturn(EntityFactory.item)

        val expectedResponse = DeleteItemWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "")

        val response = systemUnderTest.deleteItem(authToken, "itemToken")

        responsesAreEqual(response, expectedResponse)
    }

    @Test
    fun shouldReturnProperResponseWhenTryingToDeleteOtherUserItem() {
        whenever(mockOfItemsRepository.findItemByToken("itemToken")).thenReturn(EntityFactory.itemForCertainUserId(2))

        val expectedResponse = DeleteItemWebserviceResponse(HttpStatus.BAD_REQUEST,
                WSCode.ERROR_WRONG_FIELD,
                WSCode.ERROR_WRONG_FIELD.code,
                WSString.USER_ITEM_DELETE_FOR_WRONG_USER.tag)

        val response = systemUnderTest.deleteItem(authToken, "itemToken")

        responsesAreEqual(response, expectedResponse)
    }

    @Test
    fun shouldReturnProperResponseWhenItemDoesNotExistsForDelete() {
        val expectedResponse = DeleteItemWebserviceResponse(HttpStatus.BAD_REQUEST,
                WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM,
                WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM.code,
                WSString.USER_ITEM_DOES_NOT_EXISTS.tag)

        val response = systemUnderTest.deleteItem(authToken, "itemToken")

        responsesAreEqual(response, expectedResponse)
    }

    @Test
    fun shouldReturnItemsForCode() {
        whenever(mockOfItemsRepository.findItemsByCompanyCode("testCode")).thenReturn(EntityFactory.itemsForCode("testCode", 1))

        val expectedResponse = ItemsWebserviceResponse(HttpStatus.OK,
                WSCode.OK,
                WSCode.OK.code,
                "").apply {
            items = EntityFactory.itemsForCode("testCode", 1)
        }

        val response = systemUnderTest.getCompanyItems(authToken, "testCode")

        responsesAreEqual(response, expectedResponse)
    }

    @Test
    fun shouldReturnBadRequestForAnotherCompanyItems() {
        whenever(mockOfItemsRepository.findItemsByCompanyCode("testCode")).thenReturn(EntityFactory.itemsForCode("testCode", 1))

        val expectedResponse = badRequestUserTriesToGetItemsFromAnotherCompany()

        val response = systemUnderTest.getCompanyItems(authToken, "test")

        responsesAreEqualWithNullValue(response, expectedResponse)
    }

    @Test
    fun shouldReturnBadRequestForAnotherCompanyItem() {
        whenever(mockOfItemsRepository.findItemByToken("testToken")).thenReturn(EntityFactory.itemForCertainCompanyCode("ANOTHER CODE"))

        val expectedResponse = badRequestItemForAnotherCompany()

        val response = systemUnderTest.getCompanyItemByToken(authToken, "testToken")

        responsesAreEqualWithNullValue(response, expectedResponse)
    }

    @Test
    fun shouldReturnItemForCertainToken() {
        val expectedItem = EntityFactory.itemForCertainCompanyCode("testCode")
        whenever(mockOfItemsRepository.findItemByToken("testToken")).thenReturn(expectedItem)

        val expectedResponse = successCompanyItem(expectedItem)

        val response = systemUnderTest.getCompanyItemByToken(authToken, "testToken")

        responsesAreEqual(response, expectedResponse)
    }

    @Test
    fun shouldReturnBadRequestWhenItemDoesNotExists() {
        val expectedResponse = badRequestItemDoesNotExists()

        val response = systemUnderTest.getCompanyItemByToken(authToken, "testToken")

        responsesAreEqualWithNullValue(response, expectedResponse)
    }

    private fun itemsAreEqual(item: Item, expectedUser: Item) {
        Assertions.assertThat(item.companyCode).isEqualTo(expectedUser.companyCode)
        Assertions.assertThat(item.dateOfAddition).isEqualTo(expectedUser.dateOfAddition)
        Assertions.assertThat(item.description).isEqualTo(expectedUser.description)
        Assertions.assertThat(item.itemToken).isEqualTo(expectedUser.itemToken)
        Assertions.assertThat(item.name).isEqualTo(expectedUser.name)
        Assertions.assertThat(item.userSignedToItemId).isEqualTo(expectedUser.userSignedToItemId)
    }

    private fun responsesAreEqual(response: BaseWebserviceResponse, expectedResponse: BaseWebserviceResponse) {
        Assertions.assertThat(response.reason).isEqualTo(expectedResponse.reason)
        Assertions.assertThat(response.status).isEqualTo(expectedResponse.status)
        Assertions.assertThat(response.wsCodeValue).isEqualTo(expectedResponse.wsCodeValue)
        Assertions.assertThat(response.wsCode).isEqualTo(expectedResponse.wsCode)
    }

    private fun responsesAreEqual(response: ItemsWebserviceResponse, expectedResponse: ItemsWebserviceResponse) {
        Assertions.assertThat(response.reason).isEqualTo(expectedResponse.reason)
        Assertions.assertThat(response.status).isEqualTo(expectedResponse.status)
        Assertions.assertThat(response.wsCodeValue).isEqualTo(expectedResponse.wsCodeValue)
        Assertions.assertThat(response.wsCode).isEqualTo(expectedResponse.wsCode)
        Assertions.assertThat(response.items).isNotEmpty.hasSize(expectedResponse.items!!.count())
    }

    private fun responsesAreEqualWithNullValue(response: ItemsWebserviceResponse, expectedResponse: ItemsWebserviceResponse) {
        Assertions.assertThat(response.reason).isEqualTo(expectedResponse.reason)
        Assertions.assertThat(response.status).isEqualTo(expectedResponse.status)
        Assertions.assertThat(response.wsCodeValue).isEqualTo(expectedResponse.wsCodeValue)
        Assertions.assertThat(response.wsCode).isEqualTo(expectedResponse.wsCode)
        Assertions.assertThat(response.items).isNull()
    }

    private fun responsesAreEqual(response: ItemWebserviceResponse, expectedResponse: ItemWebserviceResponse) {
        Assertions.assertThat(response.reason).isEqualTo(expectedResponse.reason)
        Assertions.assertThat(response.status).isEqualTo(expectedResponse.status)
        Assertions.assertThat(response.wsCodeValue).isEqualTo(expectedResponse.wsCodeValue)
        Assertions.assertThat(response.wsCode).isEqualTo(expectedResponse.wsCode)
        Assertions.assertThat(response.item).isEqualTo(expectedResponse.item)
    }

    private fun responsesAreEqualWithNullValue(response: ItemWebserviceResponse, expectedResponse: ItemWebserviceResponse) {
        Assertions.assertThat(response.reason).isEqualTo(expectedResponse.reason)
        Assertions.assertThat(response.status).isEqualTo(expectedResponse.status)
        Assertions.assertThat(response.wsCodeValue).isEqualTo(expectedResponse.wsCodeValue)
        Assertions.assertThat(response.wsCode).isEqualTo(expectedResponse.wsCode)
        Assertions.assertThat(response.item).isNull()
    }
}