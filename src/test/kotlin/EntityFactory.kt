import model.company.CompanyAddress
import model.entity.Company
import model.entity.User
import model.workflow.request.RegisterCompanyRequest
import model.workflow.request.RegisterUserRequest

object EntityFactory {

    val user = User().apply {
        username = "test"
        password = "testPass"
        companyCode = "testCode"
        name = "testName"
        lastName = "testLastName"
    }

    val company = Company().apply {
        name = "test"
        street = "testStreet"
        streetNumber = "testStreetNumber"
        city = "testCity"
        companyNip = "testNip"
    }

    val registerUserRequest = RegisterUserRequest("test",
            "testPass",
            "testCode",
            "testName",
            "testLastName")

    val registerCompanyRequest = RegisterCompanyRequest("test",
            CompanyAddress("testStreet", "testStreetNumber", "testCity"),
            "testNip")
}