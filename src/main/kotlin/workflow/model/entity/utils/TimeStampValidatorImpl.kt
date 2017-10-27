package workflow.model.entity.utils

import javafx.util.converter.LocalDateTimeStringConverter
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime

@Service
class TimeStampValidatorImpl : TimeStampValidator {

    override fun isTimeStampValid(timeStamp: String): Boolean {
        val timeStampDateTime = LocalDateTimeStringConverter().fromString(timeStamp)
        val currentServerTime = LocalDateTime.now()

        return Duration.between(currentServerTime, timeStampDateTime).toHours() <= 1
    }
}