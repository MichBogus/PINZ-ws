package controller.company

import controller.base.BaseController
import controller.base.WSResponseEntity
import model.base.BaseWebserviceResponse
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/company")
class CompanyController : BaseController<BaseWebserviceResponse>(), CompanyControllerMappings {

    override fun getAllCompanies(): WSResponseEntity<BaseWebserviceResponse> {
        return null
    }
}