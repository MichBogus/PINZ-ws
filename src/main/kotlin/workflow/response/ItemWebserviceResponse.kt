package workflow.response

import model.base.BaseWebserviceResponse
import model.base.WSCode
import model.entity.Item
import org.springframework.http.HttpStatus

class ItemWebserviceResponse(status: HttpStatus,
                             wsCode: WSCode,
                             wsCodeValue: String,
                             reason: String): BaseWebserviceResponse(status, wsCode, wsCodeValue, reason) {

    var item: Item? = null
}