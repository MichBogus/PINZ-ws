package model

import model.base.BaseWebserviceResponse
import model.base.WSCode
import model.workflow.request.CompanyGetByCodeRequest
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.springframework.http.HttpStatus
import utils.WSString

@RunWith(Parameterized::class)
class CompanyGetByCodeRequestValidationTest(val expectedResponse: BaseWebserviceResponse,
                                            val systemUnderTest: CompanyGetByCodeRequest)  {

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
                    arrayOf(BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.COMPANY_GET_BY_CODE_INVALID.tag), CompanyGetByCodeRequest("")),
                    arrayOf(BaseWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, ""), CompanyGetByCodeRequest("1"))
            )
        }
    }
}