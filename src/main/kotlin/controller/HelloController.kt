package controller

import database.TestRepository
import model.SimpleRequest
import model.SimpleResponse
import org.springframework.beans.factory.annotation.Autowired
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

    @Autowired
    lateinit var testRepository: TestRepository

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

    @RequestMapping(value = "helloSaveMessage",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun helloSaveMessageObject(@Valid @RequestBody simpleRequest: SimpleRequest): ResponseEntity<SimpleResponse> {
        return if (simpleRequest.message.isBlank().not()) {
//            testRepository.save(TestTable(simpleRequest.name, simpleRequest.message))
            ResponseEntity(SimpleResponse(200, "saved properly"), HttpStatus.OK)
        } else {
            ResponseEntity(SimpleResponse(400, "not saved"), HttpStatus.BAD_REQUEST)
        }
    }

    @RequestMapping(value = "helloGetMessage",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun helloGetObjectByMessageObject(@Valid @RequestBody simpleRequest: SimpleRequest): ResponseEntity<SimpleResponse> {
        return if (simpleRequest.message.isBlank().not() /*&& testRepository.findByMessage(simpleRequest.message).size > 0*/) {
            ResponseEntity(SimpleResponse(200, "the object that you have gather is in table"), HttpStatus.OK)
        } else {
            ResponseEntity(SimpleResponse(400, "no such object"), HttpStatus.BAD_REQUEST)
        }
    }
}