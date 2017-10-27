package repository

import workflow.model.entity.Item
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : CrudRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE i.itemToken = :#{#itemToken}")
    fun findItemByToken(@Param("itemToken") itemToken: String): Item?

    @Query("SELECT i FROM Item i WHERE i.userSignedToItemId = :#{#userId}")
    fun findItemsByUserId(@Param("userId") userId: Long): List<Item>?

    @Query("SELECT i FROM Item i WHERE i.itemToken LIKE %:itemToken%")
    fun findItemsIfTokenOccurs(@Param("itemToken") itemToken: String): List<Item>?
}