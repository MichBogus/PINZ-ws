package service.login

import EntityFactory
import EntityFactory.loggedUser
import com.nhaarman.mockito_kotlin.*
import workflow.model.base.WSCode
import workflow.model.entity.LoggedUser
import workflow.model.entity.utils.EntityPropertyGenerator
import workflow.model.entity.utils.TimeStampValidator
import workflow.model.workflow.response.LoginUserWebserviceResponse
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.springframework.http.HttpStatus
import repository.LoggedUserRepository
import repository.UserRepository
import utils.WSString

class LoginServiceImplTest {

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
        Assertions.assertThat(argument.value.userId).isEqualTo(expectedLoggedUser.userId)
        Assertions.assertThat(argument.value.timeStamp).isEqualTo(expectedLoggedUser.timeStamp)
        Assertions.assertThat(argument.value.authToken).isEqualTo(expectedLoggedUser.authToken)
    }

    @Test
    fun shouldReturnTheSameAuthTokenAfterLoggingUserTwice() {
        val expectedToken = "ABCD TOKEN"
        val expectedResponse = LoginUserWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "").apply {
            this.authToken = expectedToken
        }
        whenever(mockOfUserRepository.findUserByUsername(any())).thenReturn(EntityFactory.user)
        whenever(mockOfLoggedUserRepository.findLoggedUserByUserId(any())).thenReturn(loggedUser(expectedToken))
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
        whenever(mockOfLoggedUserRepository.findLoggedUserByUserId(any())).thenReturn(loggedUser(expectedToken))
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
        Assertions.assertThat(response.reason).isEqualTo(expectedResponse.reason)
        Assertions.assertThat(response.status).isEqualTo(expectedResponse.status)
        Assertions.assertThat(response.wsCodeValue).isEqualTo(expectedResponse.wsCodeValue)
        Assertions.assertThat(response.wsCode).isEqualTo(expectedResponse.wsCode)
        Assertions.assertThat(response.authToken).isEqualTo(expectedResponse.authToken)
    }
}
