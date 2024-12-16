package ru.uoykaii.jointpurchasesecurity.domain.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("user_data")
data class UserDataEntity(
    @Id
    val uuid: UUID,
    val email: String,
    val password: String,
    val salt: String,
    val firstName: String,
    val lastName: String,
    val authority: UserAuthority
)

enum class UserAuthority{
    USER,
    ORGANIZER
}
