package controller.login

import controller.base.WSResponseEntity
import model.workflow.request.LoginUserRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid

interface LoginControllerMappings {

    @RequestMapping(value = "/loginUser",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun loginUser(@Valid @RequestBody request: LoginUserRequest): WSResponseEntity
}