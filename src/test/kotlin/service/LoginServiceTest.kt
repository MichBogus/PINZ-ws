package service

import EntityFactory
import EntityFactory.loggedUser
import com.nhaarman.mockito_kotlin.*
import model.base.WSCode
import model.entity.LoggedUser
import model.entity.utils.EntityPropertyGenerator
import model.entity.utils.TimeStampValidator
import model.workflow.response.LoginUserWebserviceResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.springframework.http.HttpStatus
import repository.LoggedUserRepository
import repository.UserRepository
import service.login.LoginServiceImpl
import utils.WSString


class LoginServiceTest {

    lateinit var systemUnderTest: LoginServiceImpl
    private val mockOfUserRepository = mock<UserRepository>()
    private val mockOfLoggedUserRepository = mock<LoggedUserRepository>()
    private val mockOfEntityPropertyGenerator = mock<EntityPropertyGenerator>()
    private val mockOfTimeStampValidator = mock<TimeStampValidator>()

    @Before
    fun setUp() {
        systemUnderTest = LoginServiceImpl(mockOfUserRepository,
                mockOfLoggedUserRepository,
                mockOfEntityPropertyGenerator,
                mockOfTimeStampValidator)
    }

    @Test
    fun shouldReturnUserNotRegisteredError() {
        val expectedResponse = LoginUserWebserviceResponse(HttpStatus.BAD_REQUEST,
                WSCode.ERROR_DB_ITEM_DO_NOT_EXISTS,
                WSCode.ERROR_DB_ITEM_DO_NOT_EXISTS.code,
                WSString.LOGIN_USER_DOES_NOT_EXISTS.tag)

        val response = systemUnderTest.loginUser("test", "Test123")

        responsesAreEqual(response, expectedResponse)
    }

    @Test
    fun shouldReturnPasswordNotCorrectError() {
        val expectedResponse = LoginUserWebserviceResponse(HttpStatus.BAD_REQUEST,
                WSCode.ERROR_WRONG_FIELD,
                WSCode.ERROR_WRONG_FIELD.code,
                WSString.LOGIN_PASSWORD_IS_NOT_CORRECT.tag)
        whenever(mockOfUserRepository.findUserByUsername(any())).thenReturn(EntityFactory.user)

        val response = systemUnderTest.loginUser("test", "Test123")

        responsesAreEqual(response, expectedResponse)
    }

    @Test
    fun shouldLoginNewUser() {
        val expectedToken = "ABCD TOKEN"
        val expectedResponse = LoginUserWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "").apply {
            this.authToken = expectedToken
        }
        whenever(mockOfUserRepository.findUserByUsername(any())).thenReturn(EntityFactory.user)
        whenever(mockOfEntityPropertyGenerator.generateAuthToken()).thenReturn(expectedToken)
        whenever(mockOfEntityPropertyGenerator.getCurrentServerTime()).thenReturn("DATE")

        val response = systemUnderTest.loginUser("test", "testPass")

        responsesAreEqual(response, expectedResponse)

        val argument = ArgumentCaptor.forClass(LoggedUser::class.java)
        verify(mockOfLoggedUserRepository).save(argument.capture())

        val expectedLoggedUser = loggedUser(expectedToken)
        assertThat(argument.value.userId).isEqualTo(expectedLoggedUser.userId)
        assertThat(argument.value.timeStamp).isEqualTo(expectedLoggedUser.timeStamp)
        assertThat(argument.value.authToken).isEqualTo(expectedLoggedUser.authToken)
    }

    @Test
    fun shouldReturnTheSameAuthTokenAfterLoggingUserTwice() {
        val expectedToken = "ABCD TOKEN"
        val expectedResponse = LoginUserWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "").apply {
            this.authToken = expectedToken
        }
        whenever(mockOfUserRepository.findUserByUsername(any())).thenReturn(EntityFactory.user)
        whenever(mockOfLoggedUserRepository.findLoggedUserByUserId(any())).thenReturn(EntityFactory.loggedUser(expectedToken))
        whenever(mockOfTimeStampValidator.isTimeStampValid(any())).thenReturn(true)
        whenever(mockOfEntityPropertyGenerator.generateAuthToken()).thenReturn(expectedToken)
        whenever(mockOfEntityPropertyGenerator.getCurrentServerTime()).thenReturn("DATE")

        val response = systemUnderTest.loginUser("test", "testPass")

        responsesAreEqual(response, expectedResponse)

        whenever(mockOfEntityPropertyGenerator.generateAuthToken()).thenReturn("OTHER TOKEN")

        val secondLogin = systemUnderTest.loginUser("test", "testPass")

        responsesAreEqual(secondLogin, expectedResponse)
    }

    @Test
    fun shouldCreateNewAuthTokenWhenTimeStampIsNotCorrect() {
        val expectedToken = "ABCD TOKEN"
        val expectedResponse = LoginUserWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "").apply {
            this.authToken = expectedToken
        }
        whenever(mockOfUserRepository.findUserByUsername(any())).thenReturn(EntityFactory.user)
        whenever(mockOfLoggedUserRepository.findLoggedUserByUserId(any())).thenReturn(EntityFactory.loggedUser(expectedToken))
        whenever(mockOfEntityPropertyGenerator.generateAuthToken()).thenReturn(expectedToken)
        whenever(mockOfEntityPropertyGenerator.getCurrentServerTime()).thenReturn("DATE")

        firstLoginWithWrongTimeStamp(expectedResponse)

        whenever(mockOfEntityPropertyGenerator.generateAuthToken()).thenReturn("OTHER TOKEN")
        expectedResponse.authToken = "OTHER TOKEN"

        secondLoginWithWrongTimeStamp(expectedResponse)
    }

    private fun firstLoginWithWrongTimeStamp(expectedResponse: LoginUserWebserviceResponse) {
        val response = systemUnderTest.loginUser("test", "testPass")

        val argument = ArgumentCaptor.forClass(LoggedUser::class.java)
        verify(mockOfLoggedUserRepository).save(argument.capture())
        responsesAreEqual(response, expectedResponse)
    }

    private fun secondLoginWithWrongTimeStamp(expectedResponse: LoginUserWebserviceResponse) {
        val secondLogin = systemUnderTest.loginUser("test", "testPass")

        val argument = ArgumentCaptor.forClass(LoggedUser::class.java)
        verify(mockOfLoggedUserRepository, times(2)).delete(argument.capture())
        responsesAreEqual(secondLogin, expectedResponse)
    }

    private fun responsesAreEqual(response: LoginUserWebserviceResponse, expectedResponse: LoginUserWebserviceResponse) {
        assertThat(response.reason).isEqualTo(expectedResponse.reason)
        assertThat(response.status).isEqualTo(expectedResponse.status)
        assertThat(response.wsCodeValue).isEqualTo(expectedResponse.wsCodeValue)
        assertThat(response.wsCode).isEqualTo(expectedResponse.wsCode)
        assertThat(response.authToken).isEqualTo(expectedResponse.authToken)
    }
}
