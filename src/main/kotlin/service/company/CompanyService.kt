package service.company

import model.entity.Company

interface CompanyService {

    fun getAllCompanies(): Iterable<Company>
}