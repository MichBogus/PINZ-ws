package service.token

import com.nhaarman.mockito_kotlin.mock
import model.entity.utils.TimeStampValidator
import org.junit.Before
import repository.LoggedUserRepository

class TokenValidationServiceImplTest() {

    lateinit var systemUnderTest: TokenValidationServiceImpl
    val mockOfLoggedUserRepository: LoggedUserRepository = mock()
    val mockOfTimeStampValidator: TimeStampValidator = mock()

    @Before
    fun setUp() {
        systemUnderTest = TokenValidationServiceImpl(mockOfLoggedUserRepository, mockOfTimeStampValidator, mock())
    }
}