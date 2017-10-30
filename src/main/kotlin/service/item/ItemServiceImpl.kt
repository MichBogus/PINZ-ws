package service.item

import model.entity.User
import org.springframework.stereotype.Service
import repository.ItemRepository
import repository.LoggedUserRepository
import repository.UserRepository
import service.item.ItemServiceResponses.badRequestAddItemThatExists
import service.item.ItemServiceResponses.badRequestDeleteItemDoesNotExists
import service.item.ItemServiceResponses.badRequestDeleteItemForAnotherUser
import service.item.ItemServiceResponses.badRequestItemDoesNotExists
import service.item.ItemServiceResponses.badRequestItemForAnotherCompany
import service.item.ItemServiceResponses.badRequestUserTriesToGetItemsFromAnotherCompany
import service.item.ItemServiceResponses.successAddItemResponse
import service.item.ItemServiceResponses.successCompanyItem
import service.item.ItemServiceResponses.successCompanyItems
import service.item.ItemServiceResponses.successDeleteItemResponse
import utils.converter.RequestConverter
import workflow.request.AddItemRequest
import workflow.response.AddItemWebserviceResponse
import workflow.response.ItemWebserviceResponse
import workflow.response.ItemsWebserviceResponse
import workflow.response.DeleteItemWebserviceResponse

@Service
class ItemServiceImpl(private val userItemRepository: ItemRepository,
                      private val loggedUserRepository: LoggedUserRepository,
                      private val userRepository: UserRepository,
                      private val converter: RequestConverter) : ItemService {

    override fun addItem(authToken: String, request: AddItemRequest): AddItemWebserviceResponse {
        val user = getUserByAuthToken(authToken)
        val itemToBeAdded = converter.convertAddItemRequestToEntity(request)

        if (userItemRepository.findItemByToken(request.itemToken) != null)
            return badRequestAddItemThatExists()

        itemToBeAdded.apply {
            companyCode = user.companyCode
            userSignedToItemId = user.id
        }

        userItemRepository.save(itemToBeAdded)

        return successAddItemResponse()
    }

    override fun deleteItem(authToken: String, itemToken: String): DeleteItemWebserviceResponse {
        userItemRepository.findItemByToken(itemToken)?.let {
            if (it.userSignedToItemId != getUserByAuthToken(authToken).id) {
                return badRequestDeleteItemForAnotherUser()
            }

            userItemRepository.delete(it)

            return successDeleteItemResponse()
        }

        return badRequestDeleteItemDoesNotExists()
    }

    override fun getCompanyItems(authToken: String, companyCode: String): ItemsWebserviceResponse {
        if (getUserByAuthToken(authToken).companyCode != companyCode)
            return badRequestUserTriesToGetItemsFromAnotherCompany()

        return successCompanyItems(userItemRepository.findItemsByCompanyCode(companyCode))
    }

    override fun getCompanyItemByToken(authToken: String, itemToken: String): ItemWebserviceResponse {
        userItemRepository.findItemByToken(itemToken)?.let {
            return if (it.companyCode != getUserByAuthToken(authToken).companyCode) {
                badRequestItemForAnotherCompany()
            } else {
                successCompanyItem(it)
            }
        }

        return badRequestItemDoesNotExists()
    }

    override fun getUserItems(authToken: String): ItemsWebserviceResponse {
        return successCompanyItems(userItemRepository.findItemsByUserId(getUserByAuthToken(authToken).id))
    }

    private fun getUserByAuthToken(authToken: String): User {
        val loggedUser = loggedUserRepository.findLoggedUserByAuthToken(authToken)
        return userRepository.findOne(loggedUser?.id)
    }
}