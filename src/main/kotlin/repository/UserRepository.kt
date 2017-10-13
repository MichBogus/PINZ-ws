package repository

import model.entity.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :#{#username}")
    fun findUserByUsername(@Param("username") username: String): User?
}