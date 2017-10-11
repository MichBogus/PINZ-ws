package model

import model.base.BaseWebserviceResponse
import model.base.WSCode
import model.company.CompanyAddress
import model.workflow.request.RegisterCompanyRequest
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.springframework.http.HttpStatus
import utils.WSString

@RunWith(Parameterized::class)
class RegisterCompanyValidationTest(val expectedResponse: BaseWebserviceResponse,
                                    val systemUnderTest: RegisterCompanyRequest) {

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
                    arrayOf(BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.REGISTER_COMPANY_NAME_INVALID.tag), RegisterCompanyRequest("", CompanyAddress("", "", ""), "")),
                    arrayOf(BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.REGISTER_COMPANY_ADDRESS_INVALID.tag), RegisterCompanyRequest("1234", CompanyAddress("", "", ""), "")),
                    arrayOf(BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.REGISTER_COMPANY_ADDRESS_INVALID.tag), RegisterCompanyRequest("1234", CompanyAddress("1", "", ""), "")),
                    arrayOf(BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.REGISTER_COMPANY_ADDRESS_INVALID.tag), RegisterCompanyRequest("1234", CompanyAddress("1", "2", ""), "")),
                    arrayOf(BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.REGISTER_COMPANY_NIP_INVALID.tag), RegisterCompanyRequest("1234", CompanyAddress("1", "2", "3"), "")),
                    arrayOf(BaseWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, ""), RegisterCompanyRequest("1234", CompanyAddress("1", "2", "3"), "4"))
            )
        }
    }
}