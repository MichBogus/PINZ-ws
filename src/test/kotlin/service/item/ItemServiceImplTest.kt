package service.item

import EntityFactory
import com.nhaarman.mockito_kotlin.*
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
import utils.WSString
import utils.converter.RequestConverter
import workflow.response.AddItemWebserviceResponse

class ItemServiceImplTest {

    lateinit var systemUnderTest: ItemServiceImpl
    val mockOfItemsRepository: ItemRepository = mock()
    val mockOfLoggedUserRepository: LoggedUserRepository = mock()
    val mockOfUserRepository: UserRepository = mock()
    val mockOfConverter: RequestConverter = mock()

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

    private fun itemsAreEqual(item: Item, expectedUser: Item) {
        Assertions.assertThat(item.companyCode).isEqualTo(expectedUser.companyCode)
        Assertions.assertThat(item.dateOfAddition).isEqualTo(expectedUser.dateOfAddition)
        Assertions.assertThat(item.description).isEqualTo(expectedUser.description)
        Assertions.assertThat(item.itemToken).isEqualTo(expectedUser.itemToken)
        Assertions.assertThat(item.name).isEqualTo(expectedUser.name)
        Assertions.assertThat(item.userSignedToItemId).isEqualTo(expectedUser.userSignedToItemId)
    }

    private fun responsesAreEqual(response: AddItemWebserviceResponse, expectedResponse: AddItemWebserviceResponse) {
        Assertions.assertThat(response.reason).isEqualTo(expectedResponse.reason)
        Assertions.assertThat(response.status).isEqualTo(expectedResponse.status)
        Assertions.assertThat(response.wsCodeValue).isEqualTo(expectedResponse.wsCodeValue)
        Assertions.assertThat(response.wsCode).isEqualTo(expectedResponse.wsCode)
    }
}