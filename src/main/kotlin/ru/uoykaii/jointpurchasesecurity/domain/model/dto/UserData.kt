package ru.uoykaii.jointpurchasesecurity.domain.model.dto

import ru.uoykaii.jointpurchasesecurity.domain.model.entity.UserAuthority
import ru.uoykaii.security.user.UserDataResponse
import ru.uoykaii.security.user.userDataResponse
import java.util.*

data class UserData(
    val uuid: UUID,
    val email: String,
    val firstName: String,
    val lastName: String,
    val authority: UserAuthority
)

fun UserData.getUserDataResponse(): UserDataResponse =
    userDataResponse {
        uuid = uuid
        email = email
        firstName = firstName
        lastName = lastName
        authority = ru.uoykaii.security.user.UserAuthority.valueOf(authority.name)
    }
