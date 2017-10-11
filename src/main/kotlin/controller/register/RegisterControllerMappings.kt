package controller.register

import controller.base.WSResponseEntity
import model.base.BaseWebserviceResponse
import model.workflow.request.RegisterCompanyRequest
import model.workflow.request.RegisterUserRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid

interface RegisterControllerMappings {

    @RequestMapping(value = "/registerUser",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun registerUser(@Valid @RequestBody request: RegisterUserRequest): WSResponseEntity<BaseWebserviceResponse>

    @RequestMapping(value = "/registerCompany",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun registerCompany(@Valid @RequestBody request: RegisterCompanyRequest): WSResponseEntity<BaseWebserviceResponse>
}