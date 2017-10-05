package controller

import model.SimpleRequest
import model.SimpleResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(value = "/")
class HelloController {

    @RequestMapping(value = "hello",
            method = arrayOf(RequestMethod.GET))
    fun hello(): String =
            "Just hello!"

    @RequestMapping(value = "helloPost",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun helloPost(@Valid @RequestBody simpleRequest: SimpleRequest): ResponseEntity<SimpleResponse> {
        return if (simpleRequest.name.isBlank().not()) {
            ResponseEntity(SimpleResponse(200, "test123"), HttpStatus.OK)
        } else {
            ResponseEntity(SimpleResponse(400, "bac name"), HttpStatus.BAD_REQUEST)
        }
    }
}