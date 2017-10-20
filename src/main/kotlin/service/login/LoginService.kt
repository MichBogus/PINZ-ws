package service.login

import model.workflow.response.LoginUserWebserviceResponse

interface LoginService {

    fun loginUser(username: String, password: String): LoginUserWebserviceResponse
}