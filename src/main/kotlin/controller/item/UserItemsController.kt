package controller.item

import controller.base.WSResponseEntity
import model.workflow.request.LoginUserRequest
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/item")
class UserItemsController : UserItemsControllerMappings {

    override fun addItem(request: LoginUserRequest): WSResponseEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteItem(request: LoginUserRequest): WSResponseEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCompanyItems(request: LoginUserRequest): WSResponseEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemByToken(request: LoginUserRequest): WSResponseEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemForUser(request: LoginUserRequest): WSResponseEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}