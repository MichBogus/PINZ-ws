package utils

enum class WSString(val tag: String) {

    AUTH_TOKEN_INVALID("TOKEN IS INVALID"),

    GENERIC_DB_NON_EXISTING_ITEM("There is no such element in the database"),
    GENERIC_DB_USER_EXISTS("There is another user with such username in the database or there is no such company in the database"),

    REGISTER_USER_USERNAME_INVALID("Username is empty or invalid"),
    REGISTER_USER_PASSWORD_INVALID("Password is empty or invalid (it should contains at least one number and minimum of 8 chars)"),
    REGISTER_USER_COMPANY_ID_INVALID("Company id is empty or invalid"),
    REGISTER_USER_NAME_INVALID("Name is empty or invalid"),
    REGISTER_USER_LAST_NAME_INVALID("Last name is empty or invalid"),

    REGISTER_COMPANY_NAME_INVALID("Company name is empty or invalid"),
    REGISTER_COMPANY_ADDRESS_INVALID("Address is empty or invalid"),
    REGISTER_COMPANY_NIP_INVALID("Company NIP is empty or invalid"),

    COMPANY_GET_BY_CODE_INVALID("Company code is empty or invalid"),

    LOGIN_USER_DOES_NOT_EXISTS("User does not exists, try again"),
    LOGIN_USERNAME_DOES_NOT_EXISTS("Username does not exists, try again"),
    LOGIN_PASSWORD_IS_NOT_CORRECT("Password is wrong, try again"),

    USER_ITEM_ADD_NAME_INVALID("Item name is empty or invalid"),
    USER_ITEM_ADD_DESCRIPTION_INVALID("Item description is empty or invalid"),
    USER_ITEM_ADD_DATE_INVALID("Item date of addition is invalid"),

    USER_ITEM_TOKEN_INVALID("Item token is empty or invalid"),
    USER_ITEM_EXISTS("Item already exists in the system (check token)"),
    USER_ITEM_DOES_NOT_EXISTS("Item does not exists"),
    USER_ITEM_DELETE_FOR_WRONG_USER("You are trying to delete another user item"),
    USER_ITEM_COMPANY_ITEMS_WRONG_USER("You are not able to get that resource")

}