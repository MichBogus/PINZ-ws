package model.workflow.response

import model.base.BaseWebserviceResponse
import model.base.WSCode
import model.entity.Company
import org.springframework.http.HttpStatus

class CompanyWebserviceResponse(status: HttpStatus,
                                wsCode: WSCode,
                                wsCodeValue: String,
                                reason: String) : BaseWebserviceResponse(status, wsCode, wsCodeValue, reason) {

    var companies: Iterable<Company>? = null
    var company: Company? = null

}