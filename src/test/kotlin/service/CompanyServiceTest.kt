package service

import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import repository.CompanyRepository
import service.company.CompanyServiceImpl

class CompanyServiceTest {

    lateinit var systemUnderTest: CompanyServiceImpl
    val mockOfCompanyRepository: CompanyRepository = mock()

    @Before
    fun setUp() {
        systemUnderTest = CompanyServiceImpl(mockOfCompanyRepository)
    }
}