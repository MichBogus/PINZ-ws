package workflow

import workflow.model.base.WSCode
import workflow.model.entity.Company
import workflow.model.workflow.response.CompanyWebserviceResponse
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.springframework.http.HttpStatus

class CompanyWebserviceResponseTest {

    lateinit var systemUnderTest: CompanyWebserviceResponse

    @Before
    fun setUp() {
        systemUnderTest = CompanyWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "")
    }

    @Test
    fun shouldReturnErrorWhenCompanyIsNull() {
        systemUnderTest.validateCompany(null)

        Assertions.assertThat(systemUnderTest.company).isNull()
        Assertions.assertThat(systemUnderTest.status).isEqualTo(HttpStatus.BAD_REQUEST)
        Assertions.assertThat(systemUnderTest.wsCode).isEqualTo(WSCode.ERROR_DB_ITEM_DO_NOT_EXISTS)
        Assertions.assertThat(systemUnderTest.wsCodeValue).isEqualTo(WSCode.ERROR_DB_ITEM_DO_NOT_EXISTS.code)
        Assertions.assertThat(systemUnderTest.reason).isNotEmpty()
    }

    @Test
    fun shouldReturnFilledArrayWhenCompanyListIsNotEmpty() {
        systemUnderTest.validateCompany(Company(1, "1", "1", "1", "1", "1", "1"))

        Assertions.assertThat(systemUnderTest.company).isNotNull()
        Assertions.assertThat(systemUnderTest.status).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(systemUnderTest.wsCode).isEqualTo(WSCode.OK)
        Assertions.assertThat(systemUnderTest.wsCodeValue).isEqualTo(WSCode.OK.code)
    }
}