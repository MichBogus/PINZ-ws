package model.entity.utils

import org.springframework.stereotype.Service
import java.util.*

@Service
class EntityPropertyGeneratorImpl : EntityPropertyGenerator {

    companion object {
        private val COMPANY_CODE_CHARS = "ABCDEFGHIJKLMNOPQERSTUWVXYZ0123456789".toCharArray()
    }

    override fun generateCompanyCode(): String {
        val companyCode = StringBuilder()
        for (i in 0..6) {
            companyCode.append(COMPANY_CODE_CHARS[Random().nextInt(COMPANY_CODE_CHARS.size)])
        }
        return companyCode.toString()
    }
}