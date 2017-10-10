package controller.base

import model.base.BaseWebserviceResponse
import org.springframework.http.HttpStatus

abstract class BaseController<R : BaseWebserviceResponse> {

    fun returnResponse(response: R, status: HttpStatus): WSResponseEntity<R> {
        return WSResponseEntity(response, status)
    }
}