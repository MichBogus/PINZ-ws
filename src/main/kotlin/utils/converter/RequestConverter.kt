package utils.converter

import workflow.model.entity.Company
import workflow.model.entity.User
import workflow.model.workflow.request.RegisterCompanyRequest
import workflow.model.workflow.request.RegisterUserRequest

interface RequestConverter {

    fun convertRegisterUserRequestToEntity(request: RegisterUserRequest) : User
    fun convertRegisterCompanyRequestToEntity(request: RegisterCompanyRequest) : Company
}