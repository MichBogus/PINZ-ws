package service.login

import org.springframework.stereotype.Service
import repository.UserRepository

@Service
class LoginServiceImpl(val userRepository: UserRepository) : LoginService {

    override fun loginUser(username: String, password: String): Boolean {
        val userToBeLogged = userRepository.findUserByUsername(username)

        if (userToBeLogged != null && userToBeLogged.password == password) {

        }

        return false
    }
}