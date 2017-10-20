package model.entity

import javax.persistence.*

@Entity
@Table(name = "LoggedUser")
class LoggedUser(@Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
                 var userId: Long = 0,
                 var authToken : String = "",
                 var timeStamp: String = "")