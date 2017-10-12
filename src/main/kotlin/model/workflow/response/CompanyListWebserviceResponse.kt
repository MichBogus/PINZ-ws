package model.workflow.response

import model.base.BaseWebserviceResponse
import model.base.WSCode
import model.entity.Company
import org.springframework.http.HttpStatus
import utils.WSString

class CompanyListWebserviceResponse(status: HttpStatus,
                                    wsCode: WSCode,
                                    wsCodeValue: String,
                                    reason: String) : BaseWebserviceResponse(status, wsCode, wsCodeValue, reason) {

    var companies: Iterable<Company>? = null

    fun validateCompanyList(companies: Iterable<Company>?) {
        if (companies != null && companies.count() > 0) {
            this.companies = companies
        } else {
            status = HttpStatus.BAD_REQUEST
            wsCode = WSCode.ERROR_DB_ITEM_DO_NOT_EXISTS
            wsCodeValue = WSCode.ERROR_DB_ITEM_DO_NOT_EXISTS.code
            reason = WSString.GENERIC_DB_NON_EXISTING_ITEM.tag
        }
    }

}