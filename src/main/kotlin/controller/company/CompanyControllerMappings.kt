package controller.company

import controller.base.WSResponseEntity
import workflow.request.CompanyGetByCodeRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid

interface CompanyControllerMappings {

    @RequestMapping(value = "/getAllCompanies",
            method = arrayOf(RequestMethod.GET))
    fun getAllCompanies(): WSResponseEntity

    @RequestMapping(value = "/getCompanyByCode",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun getCompanyByCode(@Valid @RequestBody request: CompanyGetByCodeRequest): WSResponseEntity
}