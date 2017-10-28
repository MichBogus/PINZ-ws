package utils.converter

import model.entity.Company
import model.entity.Item
import model.entity.User
import org.springframework.stereotype.Service
import workflow.request.AddItemRequest
import workflow.request.RegisterCompanyRequest
import workflow.request.RegisterUserRequest

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

    override fun convertAddItemRequestToEntity(request: AddItemRequest): Item =
            Item(name = request.name,
                    dateOfAddition = request.dateOfAddition,
                    description = request.description,
                    itemToken = request.itemToken)
}