import model.entity.CompanyAddress
import model.entity.Company
import model.entity.Item
import model.entity.LoggedUser
import model.entity.User
import workflow.request.AddItemRequest
import workflow.request.RegisterCompanyRequest
import workflow.request.RegisterUserRequest

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

    val addItemRequest = AddItemRequest("test",
            "testDescription",
            "testDate",
            "testToken")

    val item = Item(name = "testName",
            dateOfAddition = "testDate",
            description = "testDescription",
            userSignedToItemId = 1,
            itemToken = "testToken")

    fun itemForCertainUserId(userId: Long) =
            Item(name = "testName",
                    dateOfAddition = "testDate",
                    description = "testDescription",
                    userSignedToItemId = userId,
                    itemToken = "testToken")

    fun itemForCertainCompanyCode(companyCode: String) =
            Item(name = "testName",
                    dateOfAddition = "testDate",
                    description = "testDescription",
                    userSignedToItemId = 1,
                    companyCode = companyCode,
                    itemToken = "testToken")

    fun itemsForCode(companyCode: String, userId: Long) = listOf(
            Item(name = "testName",
                    dateOfAddition = "testDate",
                    description = "testDescription",
                    companyCode = companyCode,
                    userSignedToItemId = userId,
                    itemToken = "testToken"),
            Item(name = "testName",
                    dateOfAddition = "testDate",
                    description = "testDescription",
                    userSignedToItemId = userId,
                    itemToken = "testToken"),
            Item(name = "testName",
                    dateOfAddition = "testDate",
                    description = "testDescription",
                    userSignedToItemId = userId,
                    itemToken = "testToken"))
}