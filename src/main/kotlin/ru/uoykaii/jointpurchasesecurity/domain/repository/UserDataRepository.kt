package ru.uoykaii.jointpurchasesecurity.domain.repository

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.uoykaii.jointpurchasesecurity.domain.model.entity.UserDataEntity
import java.util.*

@Repository
interface UserDataRepository : CrudRepository<UserDataEntity, UUID> {

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): UserDataEntity?

    fun findByUuid(uuid: UUID): UserDataEntity?

    @Modifying
    @Query(
        """
        INSERT INTO user_data (uuid, email, password, salt, first_name, last_name, authority)
        VALUES (:#{#data.uuid}, :#{#data.email}, :#{#data.password}, :#{#data.salt}, :#{#data.firstName}, :#{#data.lastName}, :#{#data.authority.name})
    """
    )
    fun addUser(@Param("data") userData: UserDataEntity)
}
