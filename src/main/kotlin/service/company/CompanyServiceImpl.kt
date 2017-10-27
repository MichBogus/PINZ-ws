package service.company

import workflow.model.entity.Company
import org.springframework.stereotype.Service
import repository.CompanyRepository

@Service
class CompanyServiceImpl(val companyRepository: CompanyRepository) : CompanyService {

    override fun getAllCompanies(): Iterable<Company> =
            companyRepository.findAll()

    override fun getCompanyByCode(companyCode: String): Company
            = companyRepository.findByCompanyCode(companyCode)

}