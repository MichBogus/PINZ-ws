import workflow.model.company.CompanyAddress
import workflow.model.entity.Company
import workflow.model.entity.LoggedUser
import workflow.model.entity.User
import workflow.model.workflow.request.RegisterCompanyRequest
import workflow.model.workflow.request.RegisterUserRequest

object EntityFactory {

    val user = User().apply {
        id = 1
        username = "test"
        password = "testPass"
        companyCode = "testCode"
        name = "testName"
        lastName = "testLastName"
    }

    fun loggedUser(expectedAuthToken: String) = LoggedUser().apply {
        id = 1
        userId = 1
        authToken = expectedAuthToken
        timeStamp = "DATE"
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