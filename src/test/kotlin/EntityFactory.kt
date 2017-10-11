import model.entity.User
import model.registerworkflow.RegisterUserRequest

object EntityFactory {

    val user = User().apply {
        username = "test"
        password = "testPass"
        companyCode = "testCode"
        name = "testName"
        lastName = "testLastName"
    }

    val registerUserRequest = RegisterUserRequest("test",
            "testPass",
            "testCode",
            "testName",
            "testLastName")
}