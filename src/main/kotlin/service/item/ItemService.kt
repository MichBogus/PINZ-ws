package service.item

import workflow.request.AddItemRequest
import workflow.response.AddItemWebserviceResponse
import workflow.response.CompanyItemsWebserviceResponse
import workflow.response.DeleteItemWebserviceResponse

interface ItemService {

    fun addItem(authToken: String, request: AddItemRequest): AddItemWebserviceResponse
    fun deleteItem(authToken: String, itemToken: String): DeleteItemWebserviceResponse
    fun getCompanyItems(authToken: String, companyCode: String): CompanyItemsWebserviceResponse
}