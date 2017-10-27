package workflow.model.entity

import javax.persistence.*

@Entity
@Table(name = "Item")
class Item(@Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
           var userSignedToItemId: Long = 0,
           var name: String = "",
           var description: String = "",
           var dateOfAddition: String = "",
           var itemToken: String = "")
