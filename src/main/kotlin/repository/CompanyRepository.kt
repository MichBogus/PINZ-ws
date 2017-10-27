package repository

import model.entity.Company
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : CrudRepository<Company, Long> {

    @Query("SELECT c FROM Company c WHERE c.companyCode = :#{#companyCode}")
    fun findByCompanyCode(@Param("companyCode") companyCode: String): Company
}