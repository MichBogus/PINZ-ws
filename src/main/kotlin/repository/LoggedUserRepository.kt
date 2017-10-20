package repository

import model.entity.LoggedUser
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface LoggedUserRepository : CrudRepository<LoggedUser, Long> {

    @Query("SELECT u FROM LoggedUser u WHERE u.authToken = :#{#authToken}")
    fun findLoggedUserByAuthToken(@Param("authToken") authToken: String): LoggedUser?

    @Query("SELECT u FROM LoggedUser u WHERE u.userId = :#{#userId}")
    fun findLoggedUserByUserId(@Param("userId") userId: Long): LoggedUser?
}