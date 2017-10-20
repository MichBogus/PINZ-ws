package model.workflow.request

import com.fasterxml.jackson.annotation.JsonProperty
import model.base.BaseRequest
import model.base.BaseWebserviceResponse
import org.jetbrains.annotations.NotNull

class LoginUserRequest(@JsonProperty("username") @NotNull val username: String,
                       @JsonProperty("password") @NotNull val password: String): BaseRequest() {

    override fun checkIfRequestIsValid(): BaseWebserviceResponse {
        if (username.isNullOrEmpty()) {

        }

        if (password.isNullOrEmpty()) {

        }

        return super.checkIfRequestIsValid()
    }
}