package workflow.response

import model.base.WSCode
import model.entity.Company
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.springframework.http.HttpStatus

class CompanyListWebserviceResponseTest {

    lateinit var systemUnderTest: CompanyListWebserviceResponse

    @Before
    fun setUp() {
        systemUnderTest = CompanyListWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "")
    }

    @Test
    fun shouldReturnErrorWhenCompanyListIsEmpty() {
        systemUnderTest.validateCompanyList(arrayListOf())

        Assertions.assertThat(systemUnderTest.companies).isNull()
        Assertions.assertThat(systemUnderTest.status).isEqualTo(HttpStatus.BAD_REQUEST)
        Assertions.assertThat(systemUnderTest.wsCode).isEqualTo(WSCode.ERROR_DB_ITEM_DO_NOT_EXISTS)
        Assertions.assertThat(systemUnderTest.wsCodeValue).isEqualTo(WSCode.ERROR_DB_ITEM_DO_NOT_EXISTS.code)
        Assertions.assertThat(systemUnderTest.reason).isNotEmpty()
    }

    @Test
    fun shouldReturnFilledArrayWhenCompanyListIsNotEmpty() {
        systemUnderTest.validateCompanyList(arrayListOf(Company(1, "1", "1", "1", "1", "1", "1")))

        Assertions.assertThat(systemUnderTest.companies).isNotNull
        Assertions.assertThat(systemUnderTest.companies).isNotEmpty
        Assertions.assertThat(systemUnderTest.status).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(systemUnderTest.wsCode).isEqualTo(WSCode.OK)
        Assertions.assertThat(systemUnderTest.wsCodeValue).isEqualTo(WSCode.OK.code)
    }
}