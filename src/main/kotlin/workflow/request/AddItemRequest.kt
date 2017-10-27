package workflow.request

import com.fasterxml.jackson.annotation.JsonProperty
import model.base.BaseRequest
import model.base.BaseWebserviceResponse
import model.base.WSCode
import org.jetbrains.annotations.NotNull
import org.springframework.http.HttpStatus
import utils.WSString

class AddItemRequest(@JsonProperty("name") @NotNull val name: String,
                     @JsonProperty("description") @NotNull val description: String,
                     @JsonProperty("dateOfAddition") @NotNull val dateOfAddition: String,
                     @JsonProperty("itemToken") @NotNull val itemToken: String) : BaseRequest() {

    override fun checkIfRequestIsValid(): BaseWebserviceResponse {
        if (name.isNullOrEmpty()) {
            return BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.USER_ITEM_ADD_NAME_INVALID.tag)
        }

        if (description.isNullOrEmpty()) {
            return BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.USER_ITEM_ADD_DESCRIPTION_INVALID.tag)
        }

        if (dateOfAddition.isNullOrEmpty()) {
            return BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.USER_ITEM_ADD_DATE_INVALID.tag)
        }

        if (itemToken.isNullOrEmpty()) {
            return BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.USER_ITEM_TOKEN_INVALID.tag)
        }

        return super.checkIfRequestIsValid()
    }
}