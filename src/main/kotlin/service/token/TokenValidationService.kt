package service.token

interface TokenValidationService {

    fun validateAndUpdate(token: String): Boolean
}