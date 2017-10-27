package workflow.model.entity.utils

import javafx.util.converter.LocalDateTimeStringConverter
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class EntityPropertyGeneratorImpl : EntityPropertyGenerator {

    companion object {
        private val COMPANY_CODE_CHARS = "ABCDEFGHIJKLMNOPQERSTUWVXYZ0123456789".toCharArray()
        private val AUTHTOKEN_CHARS = "abcdefghijklmnopqerstuwvxyzABCDEFGHIJKLMNOPQERSTUWVXYZ0123456789".toCharArray()
    }

    override fun generateCompanyCode(): String {
        val companyCode = StringBuilder()
        for (i in 0..6) {
            companyCode.append(COMPANY_CODE_CHARS[Random().nextInt(COMPANY_CODE_CHARS.size)])
        }
        return companyCode.toString()
    }

    override fun generateAuthToken(): String {
        val companyCode = StringBuilder()
        for (i in 0..32) {
            companyCode.append(AUTHTOKEN_CHARS[Random().nextInt(AUTHTOKEN_CHARS.size)])
        }
        return companyCode.toString()
    }

    override fun getCurrentServerTime(): String = LocalDateTimeStringConverter().toString(LocalDateTime.now())
}