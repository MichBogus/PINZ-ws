package controller.login

import controller.base.BaseController
import controller.base.WSResponseEntity
import model.workflow.request.LoginUserRequest
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.login.LoginService

@RestController
@RequestMapping(value = "/login")
class LoginController(val loginService: LoginService) : BaseController(), LoginControllerMappings {

    override fun loginUser(request: LoginUserRequest): WSResponseEntity {
        val response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            loginService.loginUser(request.username, request.password)
        }

        return generateResponseEntity(response, response.status)
    }
}