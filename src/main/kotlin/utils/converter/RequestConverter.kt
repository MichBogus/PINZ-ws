package utils.converter

import model.entity.Company
import model.entity.Item
import model.entity.User
import workflow.request.AddItemRequest
import workflow.request.RegisterCompanyRequest
import workflow.request.RegisterUserRequest

interface RequestConverter {

    fun convertRegisterUserRequestToEntity(request: RegisterUserRequest) : User
    fun convertRegisterCompanyRequestToEntity(request: RegisterCompanyRequest) : Company
    fun convertAddItemRequestToEntity(request: AddItemRequest): Item
}