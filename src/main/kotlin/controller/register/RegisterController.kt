package controller.register

import controller.base.BaseController
import controller.base.WSResponseEntity
import model.base.BaseWebserviceResponse
import model.workflow.request.RegisterCompanyRequest
import model.workflow.request.RegisterUserRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.register.RegisterService
import javax.validation.Valid

@RestController
@RequestMapping(value = "/register")
class RegisterController(val registerService: RegisterService) : BaseController<BaseWebserviceResponse>(), RegisterControllerMappings {

    override fun registerUser(@Valid @RequestBody request: RegisterUserRequest): WSResponseEntity<BaseWebserviceResponse> {
        val response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            registerService.registerUser(request)
        }

        return super.returnResponse(response, response.status)
    }

    override fun registerCompany(@Valid @RequestBody request: RegisterCompanyRequest): WSResponseEntity<BaseWebserviceResponse> {
        val response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            registerService.registerCompany(request)
        }

        return super.returnResponse(response, response.status)
    }
}