package service

import model.registerworkflow.RegisterCompanyRequest
import model.registerworkflow.RegisterUserRequest

interface RegisterService {

    fun registerUser(request: RegisterUserRequest)
    fun registerCompany(request: RegisterCompanyRequest)
}