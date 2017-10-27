package workflow.model.workflow.response

import workflow.model.base.BaseWebserviceResponse
import workflow.model.base.WSCode
import org.springframework.http.HttpStatus

class LoginUserWebserviceResponse(status: HttpStatus,
                                  wsCode: WSCode,
                                  wsCodeValue: String,
                                  reason: String) : BaseWebserviceResponse(status, wsCode, wsCodeValue, reason) {

    var authToken: String = ""
}