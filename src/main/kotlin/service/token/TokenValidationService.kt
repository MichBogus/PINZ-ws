package service.token

interface TokenValidationService {

    fun validate(token: String): Boolean
}