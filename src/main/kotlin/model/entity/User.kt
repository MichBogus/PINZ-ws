package model.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "User")
class User(@Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
           var username: String = "",
           var password: String = "",
           var companyCode: String = "",
           var name: String = "",
           var lastName: String = "")