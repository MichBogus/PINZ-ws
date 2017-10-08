package model

import model.base.BaseWebserviceResponse
import model.base.WSCode
import model.registerworkflow.RegisterUserRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.springframework.http.HttpStatus
import utils.WSString

@RunWith(Parameterized::class)
class RegisterUserRequestValidationTest(val expectedResponse: BaseWebserviceResponse,
                                        val systemUnderTest: RegisterUserRequest) {

    @Test
    fun shouldReturnProperResponse() {
        val response = systemUnderTest.checkIfRequestIsValid()

        assertThat(response.reason).isEqualTo(expectedResponse.reason)
        assertThat(response.status).isEqualTo(expectedResponse.status)
        assertThat(response.wsCode).isEqualTo(expectedResponse.wsCode)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                    arrayOf(BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSString.REGISTER_USER_USERNAME_INVALID.tag), RegisterUserRequest("", "", "", "", "")),
                    arrayOf(BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSString.REGISTER_USER_PASSWORD_INVALID.tag), RegisterUserRequest("1234", "abcddwadwadaw", "", "", "")),
                    arrayOf(BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSString.REGISTER_USER_COMPANY_ID_INVALID.tag), RegisterUserRequest("1234", "Abcddwadwadaw1", "", "", "")),
                    arrayOf(BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSString.REGISTER_USER_NAME_INVALID.tag), RegisterUserRequest("1234", "Abcddwadwadaw1", "1", "", "")),
                    arrayOf(BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSString.REGISTER_USER_LAST_NAME_INVALID.tag), RegisterUserRequest("1234", "Abcddwadwadaw1", "1", "1", "")),
                    arrayOf(BaseWebserviceResponse(HttpStatus.OK, WSCode.OK, ""), RegisterUserRequest("1234", "Abcddwadwadaw1", "1", "1", "1"))
            )
        }
    }
}
