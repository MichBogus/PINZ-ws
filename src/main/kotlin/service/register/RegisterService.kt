package service.register

import model.workflow.request.RegisterCompanyRequest
import model.workflow.request.RegisterUserRequest

interface RegisterService {

    fun registerUser(request: RegisterUserRequest)
    fun registerCompany(request: RegisterCompanyRequest)
}