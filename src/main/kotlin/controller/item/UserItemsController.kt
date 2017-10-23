package controller.item

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/item")
class UserItemsController : UserItemsControllerMappings {
}