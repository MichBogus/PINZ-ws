package service.token

import model.entity.utils.EntityPropertyGenerator
import model.entity.utils.TimeStampValidator
import org.springframework.stereotype.Service
import repository.LoggedUserRepository

@Service
class TokenValidationServiceImpl(val loggedUserRepository: LoggedUserRepository,
                                 val timeStampValidator: TimeStampValidator,
                                 val entityPropertyGenerator: EntityPropertyGenerator) : TokenValidationService {

    override fun validateAndUpdate(token: String): Boolean {
        val user = loggedUserRepository.findLoggedUserByAuthToken(token)
        user?.let {
            return if (timeStampValidator.isTimeStampValid(it.timeStamp)) {
                user.timeStamp = entityPropertyGenerator.getCurrentServerTime()
                loggedUserRepository.save(it)
                true
            } else {
                false
            }
        }
        return false
    }
}