package utils

import model.entity.User
import model.registerworkflow.RegisterUserRequest
import org.springframework.stereotype.Service

@Service
class RequestConverterImpl : RequestConverter {

    override fun convertRegisterUserRequestToEntity(request: RegisterUserRequest): User =
            User().apply {
                username = request.username
                password = request.password
                companyCode = request.companyCode
                name = request.name
                lastName = request.lastName
            }
}