package model.workflow

import com.fasterxml.jackson.annotation.JsonProperty
import model.base.BaseRequest
import model.base.BaseWebserviceResponse
import model.base.WSCode
import org.jetbrains.annotations.NotNull
import org.springframework.http.HttpStatus
import utils.WSRegex
import utils.WSString

class RegisterUserRequest(@JsonProperty("username") @NotNull val username: String,
                          @JsonProperty("password") @NotNull val password: String,
                          @JsonProperty("companyCode") @NotNull val companyCode: String,
                          @JsonProperty("name") @NotNull val name: String,
                          @JsonProperty("lastName") @NotNull val lastName: String) : BaseRequest() {

    override fun checkIfRequestIsValid(): BaseWebserviceResponse {
        if (isUsernameValid(username).not()) {
            return BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.REGISTER_USER_USERNAME_INVALID.tag)
        }

        if (isPasswordValid(password).not()) {
            return BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.REGISTER_USER_PASSWORD_INVALID.tag)
        }

        if (companyCode.isNullOrEmpty()) {
            return BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.REGISTER_USER_COMPANY_ID_INVALID.tag)
        }

        if (name.isNullOrEmpty()) {
            return BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.REGISTER_USER_NAME_INVALID.tag)
        }

        if (lastName.isNullOrEmpty()) {
            return BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.REGISTER_USER_LAST_NAME_INVALID.tag)
        }

        return super.checkIfRequestIsValid()
    }

    private fun isUsernameValid(username: String) =
            username.isNullOrEmpty().not() && username.length > 3

    private fun isPasswordValid(password: String) =
            password.isNullOrEmpty().not() && password.contains(WSRegex.PASSWORD_REGEX.regex.toRegex())
}