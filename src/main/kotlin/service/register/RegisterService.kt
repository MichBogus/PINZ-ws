package service.register

import workflow.model.workflow.request.RegisterCompanyRequest
import workflow.model.workflow.request.RegisterUserRequest

interface RegisterService {

    fun registerUser(request: RegisterUserRequest): Boolean
    fun registerCompany(request: RegisterCompanyRequest)
}