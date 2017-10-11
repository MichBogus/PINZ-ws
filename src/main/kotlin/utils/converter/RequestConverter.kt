package utils.converter

import model.entity.Company
import model.entity.User
import model.workflow.request.RegisterCompanyRequest
import model.workflow.request.RegisterUserRequest

interface RequestConverter {

    fun convertRegisterUserRequestToEntity(request: RegisterUserRequest) : User
    fun convertRegisterCompanyRequestToEntity(request: RegisterCompanyRequest) : Company
}