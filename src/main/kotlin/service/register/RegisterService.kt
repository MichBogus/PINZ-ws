package service.register

import workflow.request.RegisterCompanyRequest
import workflow.request.RegisterUserRequest

interface RegisterService {

    fun registerUser(request: RegisterUserRequest): Boolean
    fun registerCompany(request: RegisterCompanyRequest): String
}