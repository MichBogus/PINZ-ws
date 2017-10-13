package model.company

import com.fasterxml.jackson.annotation.JsonProperty
import model.base.BaseModel

class CompanyAddress(@JsonProperty("street") val street: String,
                     @JsonProperty("streetNumber") val streetNumber: String,
                     @JsonProperty("city") val city: String) : BaseModel() {

    override fun isValid(): Boolean =
            street.isNullOrEmpty().not() && streetNumber.isNullOrEmpty().not() && city.isNullOrEmpty().not()

    override fun toString(): String =
            String.format(
                    "{street='%s', streetNumber='%s', city='%s'}",
                    street, streetNumber, city)
}