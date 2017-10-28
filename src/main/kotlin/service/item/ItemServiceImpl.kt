package service.item

import model.base.WSCode
import model.entity.User
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import repository.ItemRepository
import repository.LoggedUserRepository
import repository.UserRepository
import utils.WSString
import utils.converter.RequestConverter
import workflow.request.AddItemRequest
import workflow.response.AddItemWebserviceResponse
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
        val user = getUserByAuthToken(authToken)
        val itemToBeDeleted = userItemRepository.findItemByToken(itemToken)

        itemToBeDeleted?.let {
            if (it.userSignedToItemId != user.id) {
                return badRequestDeleteItemForAnotherUser()
            }

            userItemRepository.delete(itemToBeDeleted)

            return successDeleteItemResponse()
        }

        return badRequestDeleteItemDoesNotExists()
    }

    private fun getUserByAuthToken(authToken: String): User {
        val loggedUser = loggedUserRepository.findLoggedUserByAuthToken(authToken)
        return userRepository.findOne(loggedUser?.id)
    }

    private fun successAddItemResponse() =
            AddItemWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "")

    private fun badRequestAddItemThatExists() =
            AddItemWebserviceResponse(HttpStatus.BAD_REQUEST,
                    WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM,
                    WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM.code,
                    WSString.USER_ITEM_EXISTS.tag)

    private fun successDeleteItemResponse() =
            DeleteItemWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "")

    private fun badRequestDeleteItemForAnotherUser() =
            DeleteItemWebserviceResponse(HttpStatus.BAD_REQUEST,
                    WSCode.ERROR_WRONG_FIELD,
                    WSCode.ERROR_WRONG_FIELD.code,
                    WSString.USER_ITEM_DELETE_FOR_WRONG_USER.tag)

    private fun badRequestDeleteItemDoesNotExists() =
            DeleteItemWebserviceResponse(HttpStatus.BAD_REQUEST,
                    WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM,
                    WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM.code,
                    WSString.USER_ITEM_DOES_NOT_EXISTS.tag)
}