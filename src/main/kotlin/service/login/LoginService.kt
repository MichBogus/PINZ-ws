package service.login

interface LoginService {

    fun loginUser(username: String, password: String): Boolean
}