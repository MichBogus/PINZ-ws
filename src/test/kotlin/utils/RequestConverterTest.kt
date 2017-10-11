package utils

import EntityFactory
import model.workflow.RegisterUserRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import utils.converter.RequestConverterImpl

class RequestConverterTest {

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

        assertThat(convertedUser.name).isEqualTo(expectedUser.name)
        assertThat(convertedUser.lastName).isEqualTo(expectedUser.lastName)
        assertThat(convertedUser.username).isEqualTo(expectedUser.username)
        assertThat(convertedUser.password).isEqualTo(expectedUser.password)
        assertThat(convertedUser.companyCode).isEqualTo(expectedUser.companyCode)
    }
}