package controller.login

import controller.base.BaseController
import controller.base.WSResponseEntity
import workflow.model.workflow.request.LoginUserRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.login.LoginService
import javax.validation.Valid

@RestController
@RequestMapping(value = "/login")
class LoginController(val loginService: LoginService) : BaseController(), LoginControllerMappings {

    override fun loginUser(@Valid @RequestBody request: LoginUserRequest): WSResponseEntity {
        var response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            response = loginService.loginUser(request.username, request.password)
            return generateResponseEntity(response, response.status)
        }

        return generateResponseEntity(response, response.status)
    }
}