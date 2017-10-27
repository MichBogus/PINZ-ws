package service.login

import workflow.model.workflow.response.LoginUserWebserviceResponse

interface LoginService {

    fun loginUser(username: String, password: String): LoginUserWebserviceResponse
}