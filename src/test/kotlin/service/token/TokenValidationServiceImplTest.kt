package service.token

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import model.entity.utils.EntityPropertyGenerator
import model.entity.utils.TimeStampValidator
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import repository.LoggedUserRepository

class TokenValidationServiceImplTest {

    lateinit var systemUnderTest: TokenValidationServiceImpl
    val mockOfLoggedUserRepository: LoggedUserRepository = mock()
    val mockOfTimeStampValidator: TimeStampValidator = mock()
    val mockOfEntityPropertyGenerator: EntityPropertyGenerator = mock()

    @Before
    fun setUp() {
        systemUnderTest = TokenValidationServiceImpl(mockOfLoggedUserRepository, mockOfTimeStampValidator, mockOfEntityPropertyGenerator)
    }

    @Test
    fun returnsFalseWhenUserDoesNotExists() {
        val expected = false
        val isValid = systemUnderTest.validateAndUpdate("TOKEN")

        Assertions.assertThat(isValid).isEqualTo(expected)
    }

    @Test
    fun returnsFalseWhenTimeStampIsNotValid() {
        whenever(mockOfLoggedUserRepository.findLoggedUserByAuthToken("TOKEN")).thenReturn(EntityFactory.loggedUser("TOKEN"))
        whenever(mockOfTimeStampValidator.isTimeStampValid(any())).thenReturn(false)

        val expected = false
        val isValid = systemUnderTest.validateAndUpdate("TOKEN")

        Assertions.assertThat(isValid).isEqualTo(expected)
    }

    @Test
    fun shouldValidateProperly() {
        whenever(mockOfLoggedUserRepository.findLoggedUserByAuthToken("TOKEN")).thenReturn(EntityFactory.loggedUser("TOKEN"))
        whenever(mockOfTimeStampValidator.isTimeStampValid(any())).thenReturn(true)
        whenever(mockOfEntityPropertyGenerator.getCurrentServerTime()).thenReturn("00-00-0000")

        val expected = true
        val isValid = systemUnderTest.validateAndUpdate("TOKEN")

        Assertions.assertThat(isValid).isEqualTo(expected)
    }
}