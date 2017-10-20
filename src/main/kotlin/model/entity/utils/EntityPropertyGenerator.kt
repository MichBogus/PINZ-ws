package model.entity.utils

interface EntityPropertyGenerator {

    fun generateCompanyCode(): String

    fun generateAuthToken(): String

    fun getCurrentServerTime(): String
}