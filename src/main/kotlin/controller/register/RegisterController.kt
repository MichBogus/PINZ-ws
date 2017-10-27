package controller.register

import controller.base.BaseController
import controller.base.WSResponseEntity
import workflow.model.base.BaseWebserviceResponse
import workflow.model.base.WSCode
import workflow.model.workflow.request.RegisterCompanyRequest
import workflow.model.workflow.request.RegisterUserRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.register.RegisterService
import utils.WSString
import javax.validation.Valid

@RestController
@RequestMapping(value = "/register")
class RegisterController(val registerService: RegisterService) : BaseController(), RegisterControllerMappings {

    override fun registerUser(@Valid @RequestBody request: RegisterUserRequest): WSResponseEntity {
        val response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            val hasBeenRegistered = registerService.registerUser(request)

            if (hasBeenRegistered.not()) {
                userAlreadyExistsInSystemResponse(response)
            }
        }

        return super.generateResponseEntity(response, response.status)
    }

    private fun userAlreadyExistsInSystemResponse(response: BaseWebserviceResponse) {
        response.apply {
            status = HttpStatus.BAD_REQUEST
            wsCode = WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM
            wsCodeValue = WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM.code
            reason = WSString.GENERIC_DB_USER_EXISTS.tag
        }
    }

    override fun registerCompany(@Valid @RequestBody request: RegisterCompanyRequest): WSResponseEntity {
        val response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            registerService.registerCompany(request)
        }

        return super.generateResponseEntity(response, response.status)
    }
}