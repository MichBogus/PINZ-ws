package service.login

import model.base.WSCode
import model.entity.LoggedUser
import model.entity.User
import model.entity.utils.EntityPropertyGenerator
import model.entity.utils.TimeStampValidator
import model.workflow.response.LoginUserWebserviceResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import repository.LoggedUserRepository
import repository.UserRepository
import utils.WSString
import utils.actionableEmpty

@Service
class LoginServiceImpl(val userRepository: UserRepository,
                       val loggedUserRepository: LoggedUserRepository,
                       val entityPropertyGenerator: EntityPropertyGenerator,
                       val timeStampValidator: TimeStampValidator) : LoginService {

    override fun loginUser(username: String, password: String): LoginUserWebserviceResponse {
        val userToBeLogged = userRepository.findUserByUsername(username)

        if (userToBeLogged == null)
            return LoginUserWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_DB_ITEM_DO_NOT_EXISTS, WSCode.ERROR_DB_ITEM_DO_NOT_EXISTS.code, WSString.LOGIN_USER_DOES_NOT_EXISTS.tag)

        if (userToBeLogged.password != password)
            return LoginUserWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.LOGIN_PASSWORD_IS_NOT_CORRECT.tag)

        return isUserIsCurrentlyLoggedIn(userToBeLogged).actionableEmpty(
                { generateResponseWithAuthToken(it) },
                { generateResponseWithAuthToken(loginNewUser(userToBeLogged)) })
    }

    private fun isUserIsCurrentlyLoggedIn(userToBeLogged: User): String {
        loggedUserRepository.findLoggedUserByUserId(userToBeLogged.id)?.let { loggedUser ->
            if (timeStampValidator.isTimeStampValid(loggedUser.timeStamp).not()) {
                loggedUserRepository.delete(loggedUser)
                return ""
            }

            loggedUser.timeStamp = entityPropertyGenerator.getCurrentServerTime()

            loggedUserRepository.save(loggedUser)

            return loggedUser.authToken
        }
        return ""
    }

    private fun loginNewUser(userToBeLogged: User): String {
        val authToken = entityPropertyGenerator.generateAuthToken()
        val loggedUser = LoggedUser(userId = userToBeLogged.id,
                authToken = authToken,
                timeStamp = entityPropertyGenerator.getCurrentServerTime())
        loggedUserRepository.save(loggedUser)
        return authToken
    }

    private fun generateResponseWithAuthToken(authToken: String) =
            LoginUserWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "").apply {
                this.authToken = authToken
            }
}