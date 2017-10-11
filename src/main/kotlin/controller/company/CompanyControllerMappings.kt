package controller.company

import controller.base.WSResponseEntity
import model.base.BaseWebserviceResponse
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

interface CompanyControllerMappings {

    @RequestMapping(value = "/getAllCompanies",
            method = arrayOf(RequestMethod.GET))
    fun getAllCompanies(): WSResponseEntity<BaseWebserviceResponse>
}