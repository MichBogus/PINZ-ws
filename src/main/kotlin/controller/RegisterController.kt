package controller

import model.base.BaseWebserviceResponse
import model.registerworkflow.RegisterCompanyRequest
import model.registerworkflow.RegisterUserRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(value = "/register")
class RegisterController {


    @RequestMapping(value = "/registerUser",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun registerUser(@Valid @RequestBody request: RegisterUserRequest): ResponseEntity<BaseWebserviceResponse> {
        val response = request.checkIfRequestIsValid()

        return if (response.isOk()) {
            //TODO CREATE NEW USER TO DB!

            ResponseEntity(response, response.status)
        } else {
            ResponseEntity(response, response.status)
        }
    }

    @RequestMapping(value = "/registerCompany",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun registerCompany(@Valid @RequestBody request: RegisterCompanyRequest): ResponseEntity<BaseWebserviceResponse> {
        val response = request.checkIfRequestIsValid()

        return if (response.isOk()) {
            //TODO CREATE NEW COMPANY TO DB!

            ResponseEntity(response, response.status)
        } else {
            ResponseEntity(response, response.status)
        }
    }
}