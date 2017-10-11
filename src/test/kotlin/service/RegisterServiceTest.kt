package service

import EntityFactory
import com.nhaarman.mockito_kotlin.*
import model.entity.utils.EntityPropertyGenerator
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import repository.CompanyRepository
import repository.UserRepository
import service.register.RegisterServiceImpl
import utils.converter.RequestConverter

class RegisterServiceTest {

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

        whenever(mockOfRequestConverter.convertRegisterUserRequestToEntity(expectedRequest)).thenReturn(expectedUser)

        systemUnderTest.registerUser(expectedRequest)

        verify(mockOfUserRepository).save(expectedUser)
        verify(mockOfRequestConverter).convertRegisterUserRequestToEntity(expectedRequest)

        verifyNoMoreInteractions(mockOfUserRepository)
        verifyNoMoreInteractions(mockOfRequestConverter)
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