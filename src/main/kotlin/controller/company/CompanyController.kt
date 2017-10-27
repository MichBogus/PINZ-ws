package controller.company

import controller.base.BaseController
import controller.base.WSResponseEntity
import workflow.model.base.WSCode
import workflow.model.entity.Company
import workflow.model.workflow.request.CompanyGetByCodeRequest
import workflow.model.workflow.response.CompanyListWebserviceResponse
import workflow.model.workflow.response.CompanyWebserviceResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.company.CompanyService
import javax.validation.Valid

@RestController
@RequestMapping(value = "/company")
class CompanyController(val companyService: CompanyService) : BaseController(), CompanyControllerMappings {

    override fun getAllCompanies(): WSResponseEntity {
        val response = generateResponse(companyService.getAllCompanies())
        return generateResponseEntity(response, response.status)
    }

    override fun getCompanyByCode(@Valid @RequestBody request: CompanyGetByCodeRequest): WSResponseEntity {
        var response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            response = generateResponse(companyService.getCompanyByCode(request.companyCode))
            return generateResponseEntity(response, response.status)
        }

        return generateResponseEntity(response, response.status)
    }

    private fun generateResponse(companyList: Iterable<Company>?) =
            CompanyListWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "").apply {
                this.validateCompanyList(companyList)
            }

    private fun generateResponse(company: Company?) =
            CompanyWebserviceResponse(HttpStatus.OK, WSCode.OK, WSCode.OK.code, "").apply {
                this.validateCompany(company)
            }
}