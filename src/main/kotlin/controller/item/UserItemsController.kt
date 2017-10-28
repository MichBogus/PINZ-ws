package controller.item

import controller.base.BaseController
import controller.base.WSResponseEntity
import model.base.BaseWebserviceResponse
import model.base.WSCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.item.ItemService
import service.token.TokenValidationService
import utils.WSString
import workflow.request.AddItemRequest
import workflow.request.CompanyCodeItemsRequest
import workflow.request.DeleteItemRequest
import workflow.request.LoginUserRequest
import javax.validation.Valid

@RestController
@RequestMapping(value = "/item")
class UserItemsController(val itemService: ItemService,
                          val tokenValidationService: TokenValidationService) : BaseController(), UserItemsControllerMappings {

    override fun addItem(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String,
                         @Valid @RequestBody request: AddItemRequest): WSResponseEntity {
        if (tokenValidationService.validateAndUpdate(authToken).not())
            return authTokenInvalid()

        var response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            response = itemService.addItem(authToken, request)
            return generateResponseEntity(response, response.status)
        }

        return generateResponseEntity(response, response.status)
    }

    override fun deleteItem(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String,
                            @Valid @RequestBody request: DeleteItemRequest): WSResponseEntity {
        if (tokenValidationService.validateAndUpdate(authToken).not())
            return authTokenInvalid()

        var response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            response = itemService.deleteItem(authToken, request.itemToken)
            return generateResponseEntity(response, response.status)
        }

        return generateResponseEntity(response, response.status)
    }

    override fun getCompanyItems(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String,
                                 @Valid @RequestBody request: CompanyCodeItemsRequest): WSResponseEntity {
        if (tokenValidationService.validateAndUpdate(authToken).not())
            return authTokenInvalid()

        var response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            response = itemService.getCompanyItems(authToken, request.companyCode)
            return generateResponseEntity(response, response.status)
        }

        return generateResponseEntity(response, response.status)
    }

    override fun getItemByToken(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String,
                                request: LoginUserRequest): WSResponseEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemForUser(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String,
                                request: LoginUserRequest): WSResponseEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun authTokenInvalid(): WSResponseEntity =
            generateResponseEntity(BaseWebserviceResponse(HttpStatus.FORBIDDEN, WSCode.AUTH_TOKEN_INVALID, WSCode.AUTH_TOKEN_INVALID.code, WSString.AUTH_TOKEN_INVALID.tag), HttpStatus.FORBIDDEN)
}