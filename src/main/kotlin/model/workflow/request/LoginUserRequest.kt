package model.workflow.request

import com.fasterxml.jackson.annotation.JsonProperty
import model.base.BaseRequest
import model.base.BaseWebserviceResponse

class LoginUserRequest(@JsonProperty("username") val username: String,
                       @JsonProperty("password") val password: String): BaseRequest() {

    override fun checkIfRequestIsValid(): BaseWebserviceResponse {
        if (username.isNullOrEmpty()) {

        }

        if (password.isNullOrEmpty()) {

        }

        return super.checkIfRequestIsValid()
    }
}