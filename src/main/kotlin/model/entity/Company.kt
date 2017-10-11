package model.entity

import javax.persistence.*

@Entity
@Table(name = "Company")
class Company(@Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
              val name: String = "",
              val street: String = "",
              val streetNumber: String = "",
              val city: String = "",
              val companyNip: String = "",
              val companyCode: String = "") {

    override fun toString(): String {
        return String.format(
                "Company{id=%d, name='%s', street='%s', streetNumber='%s', city='%s', companyNip='%s', companyCode='%s'}",
                id, name, street, streetNumber, city, companyNip, companyCode)
    }
}