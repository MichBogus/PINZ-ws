package workflow.model.entity

import javax.persistence.*

@Entity
@Table(name = "Company")
class Company(@Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
              var name: String = "",
              var street: String = "",
              var streetNumber: String = "",
              var city: String = "",
              var companyNip: String = "",
              var companyCode: String = "") {

    override fun toString(): String {
        return String.format(
                "Company{id=%d, name='%s', street='%s', streetNumber='%s', city='%s', companyNip='%s', companyCode='%s'}",
                id, name, street, streetNumber, city, companyNip, companyCode)
    }
}