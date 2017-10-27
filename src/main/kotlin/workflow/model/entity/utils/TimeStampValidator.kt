package workflow.model.entity.utils

interface TimeStampValidator {

    fun isTimeStampValid(timeStamp: String): Boolean
}