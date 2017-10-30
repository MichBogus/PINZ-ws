package controller.item

import controller.base.WSResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import workflow.request.*
import javax.validation.Valid

interface UserItemsControllerMappings {

    @RequestMapping(value = "/addItem",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun addItem(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String,
                @Valid @RequestBody request: AddItemRequest): WSResponseEntity

    @RequestMapping(value = "/deleteItem",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun deleteItem(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String,
                   @Valid @RequestBody request: DeleteItemRequest): WSResponseEntity

    @RequestMapping(value = "/getAllCompanyItems",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun getCompanyItems(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String,
                        @Valid @RequestBody request: CompanyCodeItemsRequest): WSResponseEntity

    @RequestMapping(value = "/getItemByToken",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun getItemByToken(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String,
                       @Valid @RequestBody request: ItemTokenRequest): WSResponseEntity

    @RequestMapping(value = "/getUserItems",
            method = arrayOf(RequestMethod.GET))
    fun getUserItems(@Valid @RequestHeader(value = "AUTH_TOKEN") authToken: String): WSResponseEntity
}