package model.base

import org.springframework.http.HttpStatus

class BaseWebserviceResponse(val status: HttpStatus,
                             val wsCode: WSCode,
                             val reason: String) {

    fun isOk() = wsCode == WSCode.OK
}

enum class WSCode(val code: String) {
    OK("200"),

    ERROR_WRONG_FIELD("100-01"),
    ERROR_MISSING_FIELD("100-02"),

    ERROR_WRONG_TYPE("200-01"),
    ERROR_MISSING_TYPE("200-02")
}