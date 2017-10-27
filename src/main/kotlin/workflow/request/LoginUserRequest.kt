package workflow.request

import com.fasterxml.jackson.annotation.JsonProperty
import model.base.BaseRequest
import model.base.BaseWebserviceResponse
import model.base.WSCode
import org.jetbrains.annotations.NotNull
import org.springframework.http.HttpStatus
import utils.WSString

class LoginUserRequest(@JsonProperty("username") @NotNull val username: String,
                       @JsonProperty("password") @NotNull val password: String): BaseRequest() {

    override fun checkIfRequestIsValid(): BaseWebserviceResponse {
        if (username.isNullOrEmpty()) {
            return BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.LOGIN_USERNAME_DOES_NOT_EXISTS.tag)
        }

        if (password.isNullOrEmpty()) {
            return BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.LOGIN_PASSWORD_IS_NOT_CORRECT.tag)
        }

        return super.checkIfRequestIsValid()
    }
}