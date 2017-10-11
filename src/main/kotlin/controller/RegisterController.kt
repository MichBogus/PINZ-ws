package controller

import controller.base.BaseController
import controller.base.WSResponseEntity
import model.base.BaseWebserviceResponse
import model.registerworkflow.RegisterCompanyRequest
import model.registerworkflow.RegisterUserRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.RegisterService
import javax.validation.Valid

@RestController
@RequestMapping(value = "/register")
class RegisterController(val registerService: RegisterService) : BaseController<BaseWebserviceResponse>(), RegisterControllerMappings {

    override fun registerUser(@Valid @RequestBody request: RegisterUserRequest): WSResponseEntity<BaseWebserviceResponse> {
        val response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            registerService.registerUser()
        }

        return super.returnResponse(response, response.status)
    }

    override fun registerCompany(@Valid @RequestBody request: RegisterCompanyRequest): WSResponseEntity<BaseWebserviceResponse> {
        val response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            //TODO CREATE NEW COMPANY TO DB!
        }

        return super.returnResponse(response, response.status)
    }
}