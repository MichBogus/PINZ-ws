package service.item

import model.base.WSCode
import model.entity.Item
import org.springframework.http.HttpStatus
import utils.WSString
import workflow.response.AddItemWebserviceResponse
import workflow.response.ItemWebserviceResponse
import workflow.response.ItemsWebserviceResponse
import workflow.response.DeleteItemWebserviceResponse

object ItemServiceResponses {

    fun successAddItemResponse() =
            AddItemWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "")

    fun badRequestAddItemThatExists() =
            AddItemWebserviceResponse(HttpStatus.BAD_REQUEST,
                    WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM,
                    WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM.code,
                    WSString.USER_ITEM_EXISTS.tag)

    fun successDeleteItemResponse() =
            DeleteItemWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "")

    fun badRequestDeleteItemForAnotherUser() =
            DeleteItemWebserviceResponse(HttpStatus.BAD_REQUEST,
                    WSCode.ERROR_WRONG_FIELD,
                    WSCode.ERROR_WRONG_FIELD.code,
                    WSString.USER_ITEM_DELETE_FOR_WRONG_USER.tag)

    fun badRequestDeleteItemDoesNotExists() =
            DeleteItemWebserviceResponse(HttpStatus.BAD_REQUEST,
                    WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM,
                    WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM.code,
                    WSString.USER_ITEM_DOES_NOT_EXISTS.tag)

    fun successCompanyItems(items: Iterable<Item>?) =
            ItemsWebserviceResponse(HttpStatus.OK,
                    WSCode.OK,
                    WSCode.OK.code,
                    "").apply {
                this.items = items
            }

    fun badRequestUserTriesToGetItemsFromAnotherCompany() =
            ItemsWebserviceResponse(HttpStatus.BAD_REQUEST,
                    WSCode.ERROR_WRONG_FIELD,
                    WSCode.ERROR_WRONG_FIELD.code,
                    WSString.USER_ITEM_COMPANY_ITEM_WRONG_RESOURCE.tag)

    fun badRequestItemDoesNotExists() =
            ItemWebserviceResponse(HttpStatus.BAD_REQUEST,
                    WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM,
                    WSCode.ERROR_DB_ITEM_EXISTS_IN_SYSTEM.code,
                    WSString.USER_ITEM_DOES_NOT_EXISTS.tag)

    fun badRequestItemForAnotherCompany() =
            ItemWebserviceResponse(HttpStatus.BAD_REQUEST,
                    WSCode.ERROR_WRONG_FIELD,
                    WSCode.ERROR_WRONG_FIELD.code,
                    WSString.USER_ITEM_COMPANY_ITEM_WRONG_RESOURCE.tag)

    fun successCompanyItem(item: Item) =
            ItemWebserviceResponse(HttpStatus.OK,
                    WSCode.OK,
                    WSCode.OK.code,
                    "").apply {
                this.item = item
            }
}