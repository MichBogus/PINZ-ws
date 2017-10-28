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
class CompanyCodeItemsRequestTest(val expectedResponse: BaseWebserviceResponse,
                                  val systemUnderTest: CompanyCodeItemsRequest) {

    @Test
    fun shouldValidateProperly() {
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
                    arrayOf(BaseWebserviceResponse(HttpStatus.BAD_REQUEST, WSCode.ERROR_WRONG_FIELD, WSCode.ERROR_WRONG_FIELD.code, WSString.COMPANY_GET_BY_CODE_INVALID.tag), CompanyCodeItemsRequest("")),
                    arrayOf(BaseWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, ""), CompanyCodeItemsRequest("1"))
            )
        }
    }

}