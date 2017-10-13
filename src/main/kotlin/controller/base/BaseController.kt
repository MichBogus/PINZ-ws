package controller.base

import model.base.BaseWebserviceResponse
import org.springframework.http.HttpStatus

abstract class BaseController {

    fun generateResponseEntity(response: BaseWebserviceResponse, status: HttpStatus): WSResponseEntity {
        return WSResponseEntity(response, status)
    }
}