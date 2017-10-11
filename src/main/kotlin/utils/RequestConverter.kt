package utils

import model.entity.User
import model.registerworkflow.RegisterUserRequest

interface RequestConverter {

    fun convertRegisterUserRequestToEntity(request: RegisterUserRequest) : User
}