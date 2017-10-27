package service.company

import workflow.model.entity.Company

interface CompanyService {

    fun getAllCompanies(): Iterable<Company>
    fun getCompanyByCode(companyCode: String): Company?
}