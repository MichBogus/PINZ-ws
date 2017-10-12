package controller.company

import controller.base.BaseController
import controller.base.WSResponseEntity
import model.base.WSCode
import model.entity.Company
import model.workflow.request.CompanyGetByCodeRequest
import model.workflow.response.CompanyListWebserviceResponse
import model.workflow.response.CompanyWebserviceResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.company.CompanyService
import javax.validation.Valid

@RestController
@RequestMapping(value = "/company")
class CompanyController(val companyService: CompanyService) : BaseController(), CompanyControllerMappings {

    override fun getAllCompanies(): WSResponseEntity
            = returnResponse(generateResponse(companyService.getAllCompanies()), HttpStatus.OK)

    override fun getCompanyByCode(@Valid @RequestBody request: CompanyGetByCodeRequest): WSResponseEntity {
        val response = request.checkIfRequestIsValid()

        if (response.isOk()) {
            returnResponse(generateResponse(companyService.getCompanyByCode(request.companyCode)), HttpStatus.OK)
        }

        return returnResponse(response as CompanyWebserviceResponse, response.status)
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