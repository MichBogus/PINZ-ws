package database

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
interface TestRepository : CrudRepository<TestTable, Long> {

    fun findByMessage(message: String): MutableList<TestTable>
}