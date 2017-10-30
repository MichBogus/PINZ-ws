package workflow.response

import model.base.BaseWebserviceResponse
import model.base.WSCode
import model.entity.Item
import org.springframework.http.HttpStatus
import utils.WSString

class ItemsWebserviceResponse(status: HttpStatus,
                              wsCode: WSCode,
                              wsCodeValue: String,
                              reason: String) : BaseWebserviceResponse(status, wsCode, wsCodeValue, reason) {

    var items: Iterable<Item>? = null
}