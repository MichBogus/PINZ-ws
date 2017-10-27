package utils.converter

import workflow.model.entity.Company
import workflow.model.entity.User
import workflow.model.workflow.request.RegisterCompanyRequest
import workflow.model.workflow.request.RegisterUserRequest
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