package repository

import workflow.model.entity.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {

    @Query("FROM User u WHERE u.username = :#{#username}")
    fun findUserByUsername(@Param("username") usernameParam: String): User?
}