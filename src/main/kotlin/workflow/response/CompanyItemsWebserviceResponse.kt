package workflow.response

import model.base.BaseWebserviceResponse
import model.base.WSCode
import model.entity.Item
import org.springframework.http.HttpStatus
import utils.WSString

class CompanyItemsWebserviceResponse(status: HttpStatus,
                                     wsCode: WSCode,
                                     wsCodeValue: String,
                                     reason: String) : BaseWebserviceResponse(status, wsCode, wsCodeValue, reason) {

    var items: Iterable<Item>? = null

    fun validateCompanyList(companies: Iterable<Item>?) {
        if (companies != null && companies.count() > 0) {
            this.items = companies
        } else {
            status = HttpStatus.BAD_REQUEST
            wsCode = WSCode.ERROR_DB_ITEM_DO_NOT_EXISTS
            wsCodeValue = WSCode.ERROR_DB_ITEM_DO_NOT_EXISTS.code
            reason = WSString.GENERIC_DB_NON_EXISTING_ITEM.tag
        }
    }

}