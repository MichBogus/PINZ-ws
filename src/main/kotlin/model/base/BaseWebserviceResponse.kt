package model.base

import org.springframework.http.HttpStatus

open class BaseWebserviceResponse(var status: HttpStatus,
                             var wsCode: WSCode,
                             var wsCodeValue: String,
                             var reason: String) {

    fun isOk() = wsCode == WSCode.OK
}

enum class WSCode(val code: String) {
    OK("200"),

    ERROR_WRONG_FIELD("100-01"),
    ERROR_MISSING_FIELD("100-02"),

    ERROR_WRONG_TYPE("200-01"),
    ERROR_MISSING_TYPE("200-02"),

    ERROR_DB_ITEM_DO_NOT_EXISTS("300-01")
}