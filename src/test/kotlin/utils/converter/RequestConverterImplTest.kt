package utils.converter

import EntityFactory
import model.entity.CompanyAddress
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import workflow.request.RegisterCompanyRequest
import workflow.request.RegisterUserRequest

class RequestConverterImplTest {

    lateinit var systemUnderTest: RequestConverterImpl

    @Before
    fun setUp() {
        systemUnderTest = RequestConverterImpl()
    }

    @Test
    fun shouldReturnProperUserEntity() {
        val expectedUser = EntityFactory.user

        val registerRequest = RegisterUserRequest("test", "testPass", "testCode", "testName", "testLastName")

        val convertedUser = systemUnderTest.convertRegisterUserRequestToEntity(registerRequest)

        Assertions.assertThat(convertedUser.name).isEqualTo(expectedUser.name)
        Assertions.assertThat(convertedUser.lastName).isEqualTo(expectedUser.lastName)
        Assertions.assertThat(convertedUser.username).isEqualTo(expectedUser.username)
        Assertions.assertThat(convertedUser.password).isEqualTo(expectedUser.password)
        Assertions.assertThat(convertedUser.companyCode).isEqualTo(expectedUser.companyCode)
    }

    @Test
    fun shouldReturnProperCompanyEntity() {
        val expectedCompany = EntityFactory.company

        val registerRequest = RegisterCompanyRequest("test", CompanyAddress("testStreet", "testStreetNumber", "testCity"), "testNip")

        val convertedCompany = systemUnderTest.convertRegisterCompanyRequestToEntity(registerRequest)

        Assertions.assertThat(convertedCompany.name).isEqualTo(expectedCompany.name)
        Assertions.assertThat(convertedCompany.city).isEqualTo(expectedCompany.city)
        Assertions.assertThat(convertedCompany.companyNip).isEqualTo(expectedCompany.companyNip)
        Assertions.assertThat(convertedCompany.street).isEqualTo(expectedCompany.street)
        Assertions.assertThat(convertedCompany.streetNumber).isEqualTo(expectedCompany.streetNumber)
    }
}