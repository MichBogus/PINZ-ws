package service

import EntityFactory
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import repository.UserRepository
import utils.RequestConverter

class RegisterServiceTest {

    lateinit var systemUnderTest: RegisterServiceImpl

    val mockOfUserRepository: UserRepository = mock()
    val mockOfRequestConverter: RequestConverter = mock()

    @Before
    fun setUp() {
        systemUnderTest = RegisterServiceImpl(mockOfUserRepository, mockOfRequestConverter)
    }

    @Test
    fun shouldRegisterUserProperly() {
        val expectedUser = EntityFactory.user
        val expectedRequest = EntityFactory.registerUserRequest

        whenever(mockOfRequestConverter.convertRegisterUserRequestToEntity(any())).thenReturn(expectedUser)

        systemUnderTest.registerUser(expectedRequest)

        verify(mockOfUserRepository).save(expectedUser)
        verify(mockOfRequestConverter).convertRegisterUserRequestToEntity(expectedRequest)
        verifyNoMoreInteractions(mockOfUserRepository)
        verifyNoMoreInteractions(mockOfRequestConverter)
    }
}