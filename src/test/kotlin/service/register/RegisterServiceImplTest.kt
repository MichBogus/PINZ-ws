package service.register

import EntityFactory
import com.nhaarman.mockito_kotlin.*
import model.entity.utils.EntityPropertyGenerator
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import repository.CompanyRepository
import repository.UserRepository
import utils.converter.RequestConverter

class RegisterServiceImplTest {

    lateinit var systemUnderTest: RegisterServiceImpl

    val mockOfUserRepository: UserRepository = mock()
    val mockOfCompanyRepository: CompanyRepository = mock()
    val mockOfRequestConverter: RequestConverter = mock()
    val mockOfEntityPropertyGenerator: EntityPropertyGenerator = mock()

    @Before
    fun setUp() {
        systemUnderTest = spy(RegisterServiceImpl(mockOfUserRepository, mockOfCompanyRepository, mockOfRequestConverter, mockOfEntityPropertyGenerator))
    }

    @Test
    fun shouldRegisterUserProperly() {
        val expectedUser = EntityFactory.user
        val expectedRequest = EntityFactory.registerUserRequest

        whenever(mockOfUserRepository.findUserByUsername(any())).thenReturn(null)
        whenever(mockOfCompanyRepository.findByCompanyCode(any())).thenReturn(EntityFactory.company)
        whenever(mockOfRequestConverter.convertRegisterUserRequestToEntity(expectedRequest)).thenReturn(expectedUser)

        val userRegistered = systemUnderTest.registerUser(expectedRequest)

        verify(mockOfRequestConverter).convertRegisterUserRequestToEntity(expectedRequest)
        verify(mockOfUserRepository).save(expectedUser)
        Assertions.assertThat(userRegistered).isTrue()
    }

    @Test
    fun shouldNotRegisterUserWhenThereIsNoCompany() {
        val expectedUser = EntityFactory.user
        val expectedRequest = EntityFactory.registerUserRequest

        whenever(mockOfUserRepository.findUserByUsername(any())).thenReturn(null)
        whenever(mockOfRequestConverter.convertRegisterUserRequestToEntity(expectedRequest)).thenReturn(expectedUser)

        val userRegistered = systemUnderTest.registerUser(expectedRequest)

        verify(mockOfRequestConverter).convertRegisterUserRequestToEntity(expectedRequest)
        verify(mockOfUserRepository, never()).save(expectedUser)
        Assertions.assertThat(userRegistered).isFalse()
    }

    @Test
    fun shouldNotRegisterUserWhenHeAlreadyExists() {
        val expectedUser = EntityFactory.user
        val expectedRequest = EntityFactory.registerUserRequest

        whenever(mockOfUserRepository.findUserByUsername(any())).thenReturn(expectedUser)
        whenever(mockOfRequestConverter.convertRegisterUserRequestToEntity(expectedRequest)).thenReturn(expectedUser)

        val userRegistered = systemUnderTest.registerUser(expectedRequest)

        verify(mockOfRequestConverter).convertRegisterUserRequestToEntity(expectedRequest)
        verify(mockOfUserRepository, never()).save(expectedUser)
        Assertions.assertThat(userRegistered).isFalse()
    }

    @Test
    fun shouldRegisterCompanyProperly() {
        val expectedCompany = EntityFactory.company
        val expectedRequest = EntityFactory.registerCompanyRequest

        whenever(mockOfRequestConverter.convertRegisterCompanyRequestToEntity(expectedRequest)).thenReturn(expectedCompany)
        whenever(mockOfEntityPropertyGenerator.generateCompanyCode()).thenReturn("TESTCODE")

        systemUnderTest.registerCompany(expectedRequest)

        verify(mockOfRequestConverter).convertRegisterCompanyRequestToEntity(expectedRequest)
        Assertions.assertThat(expectedCompany.companyCode).isEqualTo("TESTCODE")
        verify(mockOfCompanyRepository).save(expectedCompany)

        verifyNoMoreInteractions(mockOfCompanyRepository)
        verifyNoMoreInteractions(mockOfRequestConverter)
    }
}