package controller

import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @RequestMapping(value = "/hello", method = arrayOf(RequestMethod.GET))
    fun hello(): String =
            "Just hello!"
}