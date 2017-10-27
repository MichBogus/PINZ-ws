package controller.item

import controller.base.BaseController
import controller.base.WSResponseEntity
import workflow.model.workflow.request.AddItemRequest
import workflow.model.workflow.request.DeleteItemRequest
import workflow.model.workflow.request.LoginUserRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.item.ItemService
import javax.validation.Valid

@RestController
@RequestMapping(value = "/item")
class UserItemsController(val itemService: ItemService) : BaseController(), UserItemsControllerMappings {

    override fun addItem(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String,
                         @Valid @RequestBody request: AddItemRequest): WSResponseEntity {
        var response = request.checkIfRequestIsValid()

        if (response.isOk()) {

        }

        return generateResponseEntity(response, response.status)
    }

    override fun deleteItem(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String,
                            @Valid @RequestBody request: DeleteItemRequest): WSResponseEntity {
        var response = request.checkIfRequestIsValid()

        if (response.isOk()) {

        }

        return generateResponseEntity(response, response.status)
    }

    override fun getCompanyItems(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String,
                                 request: LoginUserRequest): WSResponseEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemByToken(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String,
                                request: LoginUserRequest): WSResponseEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemForUser(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String,
                                request: LoginUserRequest): WSResponseEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}