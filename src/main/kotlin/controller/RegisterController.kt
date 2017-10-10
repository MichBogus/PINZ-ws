package controller

import controller.base.BaseController
import controller.base.WSResponseEntity
import model.base.BaseWebserviceResponse
import model.registerworkflow.RegisterCompanyRequest
import model.registerworkflow.RegisterUserRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(value = "/register")
class RegisterController: BaseController<BaseWebserviceResponse>() {

    @RequestMapping(value = "/registerUser",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun registerUser(@Valid @RequestBody request: RegisterUserRequest): WSResponseEntity<BaseWebserviceResponse> {
        val response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            //TODO CREATE NEW USER TO DB!
        }

        return super.returnResponse(response, response.status)
    }

    @RequestMapping(value = "/registerCompany",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun registerCompany(@Valid @RequestBody request: RegisterCompanyRequest): WSResponseEntity<BaseWebserviceResponse> {
        val response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            //TODO CREATE NEW COMPANY TO DB!
        }

        return super.returnResponse(response, response.status)
    }
}