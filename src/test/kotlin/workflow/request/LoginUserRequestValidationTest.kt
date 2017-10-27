package workflow.request

import model.base.BaseWebserviceResponse
import model.base.WSCode
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.springframework.http.HttpStatus
import utils.WSString

@RunWith(Parameterized::class)
class LoginUserRequestValidationTest(val expectedResponse: BaseWebserviceResponse,
                                     val systemUnderTest: LoginUserRequest) {

    @Test
    fun shouldReturnProperResponse() {
        val response = systemUnderTest.checkIfRequestIsValid()

        Assertions.assertThat(response.reason).isEqualTo(expectedResponse.reason)
        Assertions.assertThat(response.status).isEqualTo(expectedResponse.status)
        Assertions.assertThat(response.wsCode).isEqualTo(expectedResponse.wsCode)
        Assertions.assertThat(response.wsCodeValue).isEqualTo(expectedResponse.wsCodeValue)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                    arrayOf(BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.LOGIN_USERNAME_DOES_NOT_EXISTS.tag), LoginUserRequest("", "")),
                    arrayOf(BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.LOGIN_PASSWORD_IS_NOT_CORRECT.tag), LoginUserRequest("1", "")),
                    arrayOf(BaseWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, ""), LoginUserRequest("1", "1"))
            )
        }
    }
}