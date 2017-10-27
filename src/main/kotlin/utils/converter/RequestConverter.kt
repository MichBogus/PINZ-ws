package utils.converter

import model.entity.Company
import model.entity.User
import workflow.request.RegisterCompanyRequest
import workflow.request.RegisterUserRequest

interface RequestConverter {

    fun convertRegisterUserRequestToEntity(request: RegisterUserRequest) : User
    fun convertRegisterCompanyRequestToEntity(request: RegisterCompanyRequest) : Company
}