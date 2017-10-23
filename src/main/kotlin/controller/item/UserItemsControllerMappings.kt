package controller.item

import controller.base.WSResponseEntity
import model.workflow.request.LoginUserRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid

interface UserItemsControllerMappings {

    @RequestMapping(value = "/addItem",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun addItem(@Valid @RequestBody request: LoginUserRequest): WSResponseEntity

    @RequestMapping(value = "/deleteItem",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun deleteItem(@Valid @RequestBody request: LoginUserRequest): WSResponseEntity

    @RequestMapping(value = "/getAllCompanyItems",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun getCompanyItems(@Valid @RequestBody request: LoginUserRequest): WSResponseEntity

    @RequestMapping(value = "/getItemByToken",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun getItemByToken(@Valid @RequestBody request: LoginUserRequest): WSResponseEntity

    @RequestMapping(value = "/getItemForUser",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun getItemForUser(@Valid @RequestBody request: LoginUserRequest): WSResponseEntity
}