package utils.converter

import workflow.request.RegisterUserRequest
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

class RequestConverterImplTest{

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
}