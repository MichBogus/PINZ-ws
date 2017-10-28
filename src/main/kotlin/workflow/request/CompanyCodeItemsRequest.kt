package workflow.request

import com.fasterxml.jackson.annotation.JsonProperty
import model.base.BaseRequest
import model.base.BaseWebserviceResponse
import model.base.WSCode
import org.jetbrains.annotations.NotNull
import org.springframework.http.HttpStatus
import utils.WSString

class CompanyCodeItemsRequest(@JsonProperty("companyCode") @NotNull val companyCode: String) : BaseRequest() {

    override fun checkIfRequestIsValid(): BaseWebserviceResponse {
        if (companyCode.isNullOrEmpty()) {
            return BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.COMPANY_GET_BY_CODE_INVALID.tag)
        }

        return super.checkIfRequestIsValid()
    }
}