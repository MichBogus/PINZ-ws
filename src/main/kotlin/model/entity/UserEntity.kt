package model.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "User")
class UserEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var username: String = ""
    var password: String = ""
    var companyCode: String = ""
    var name: String = ""
    var lastName: String = ""

    override fun toString(): String {
        return String.format(
                "User{id=%d, username='%s', password='%s', companyCode='%s', name='%s', lastName='%s'}",
                id, username, password, companyCode, name, lastName)
    }

}