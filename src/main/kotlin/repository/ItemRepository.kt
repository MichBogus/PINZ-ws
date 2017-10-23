package repository

import model.entity.Item
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : CrudRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE i.itemToken = :#{#itemToken}")
    fun findItemByToken(@Param("itemToken") itemToken: String): Item?

    @Query("SELECT i FROM Item i WHERE i.userSignedToItemId = :#{#userId}")
    fun findItesByUserId(@Param("userId") userId: Long): List<Item>?
}