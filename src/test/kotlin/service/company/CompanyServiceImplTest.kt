package service.company

import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.*
import org.junit.Before
import repository.CompanyRepository

class CompanyServiceImplTest{

    lateinit var systemUnderTest: CompanyServiceImpl
    val mockOfCompanyRepository: CompanyRepository = mock()

    @Before
    fun setUp() {
        systemUnderTest = CompanyServiceImpl(mockOfCompanyRepository)
    }
}