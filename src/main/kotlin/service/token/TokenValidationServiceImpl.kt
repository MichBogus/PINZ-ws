package service.token

import workflow.model.entity.utils.TimeStampValidator
import org.springframework.stereotype.Service
import repository.LoggedUserRepository

@Service
class TokenValidationServiceImpl(val loggedUserRepository: LoggedUserRepository,
                                 val timeStampValidator: TimeStampValidator) : TokenValidationService {

    override fun validate(token: String): Boolean {
        val user = loggedUserRepository.findLoggedUserByAuthToken(token)
        user?.let {
            return timeStampValidator.isTimeStampValid(user.timeStamp)
        }
        return false
    }
}