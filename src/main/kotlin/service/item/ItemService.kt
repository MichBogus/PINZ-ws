package service.item

import workflow.request.AddItemRequest
import workflow.response.AddItemWebserviceResponse

interface ItemService {

    fun addItem(authToken: String, request: AddItemRequest): AddItemWebserviceResponse
    fun deleteItem()
}