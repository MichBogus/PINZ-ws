package service.register

import model.workflow.RegisterCompanyRequest
import model.workflow.RegisterUserRequest

interface RegisterService {

    fun registerUser(request: RegisterUserRequest)
    fun registerCompany(request: RegisterCompanyRequest)
}