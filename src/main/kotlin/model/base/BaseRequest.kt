package model.base

import org.springframework.http.HttpStatus

abstract class BaseRequest {

    open fun checkIfRequestIsValid(): BaseWebserviceResponse {
        return BaseWebserviceResponse(HttpStatus.OK, WSCode.OK, "")
    }
}