package utils

enum class WSString(val tag: String) {

    GENERIC_DB_NON_EXISTING_ITEM("There is no such element in the database"),

    REGISTER_USER_USERNAME_INVALID("Username is empty or invalid"),
    REGISTER_USER_PASSWORD_INVALID("Password is empty or invalid (it should contains at least one number and minimum of 8 chars)"),
    REGISTER_USER_COMPANY_ID_INVALID("Company id is empty or invalid"),
    REGISTER_USER_NAME_INVALID("Name is empty or invalid"),
    REGISTER_USER_LAST_NAME_INVALID("Last name is empty or invalid"),

    REGISTER_COMPANY_NAME_INVALID("Company name is empty or invalid"),
    REGISTER_COMPANY_ADDRESS_INVALID("Address is empty or invalid"),
    REGISTER_COMPANY_NIP_INVALID("Company NIP is empty or invalid"),

    COMPANY_GET_BY_CODE_INVALID("Company code is empty or invalid")

}