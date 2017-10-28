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

@Service
class ItemServiceImpl(private val userItemRepository: ItemRepository,
                      private val loggedUserRepository: LoggedUserRepository,
                      private val userRepository: UserRepository,
                      private val converter: RequestConverter) : ItemService {

    override fun addItem(authToken: String, request: AddItemRequest): AddItemWebserviceResponse {
        val user = getUserByAuthToken(authToken)
        val itemToBeAdded = converter.convertAddItemRequestToEntity(request)

        if (userItemRepository.findItemByToken(request.itemToken) != null)
            return AddItemWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM, WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM.code, WSString.USER_ITEM_EXISTS.tag)

        itemToBeAdded.apply {
            companyCode = user.companyCode
            userSignedToItemId = user.id
        }

        userItemRepository.save(itemToBeAdded)

        return AddItemWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "")
    }

    override fun deleteItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getUserByAuthToken(authToken: String): User {
        val loggedUser = loggedUserRepository.findLoggedUserByAuthToken(authToken)
        return userRepository.findOne(loggedUser?.id)
    }
}