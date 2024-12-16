package ru.uoykaii.jointpurchasesecurity.domain.model.dto

import java.util.*

data class UserPrivateData(
    val uuid: UUID,
    val email: String,
    val password: String,
    val salt: String,
    val firstName: String,
    val lastName: String
)
