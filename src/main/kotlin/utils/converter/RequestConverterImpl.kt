package utils.converter

import model.entity.Company
import model.entity.User
import model.workflow.RegisterCompanyRequest
import model.workflow.RegisterUserRequest
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

    override fun convertRegisterCompanyRequestToEntity(request: RegisterCompanyRequest): Company =
            Company(name = request.name,
                    street = request.address.street,
                    streetNumber = request.address.streetNumber,
                    city = request.address.city,
                    companyNip = request.companyNip)
}