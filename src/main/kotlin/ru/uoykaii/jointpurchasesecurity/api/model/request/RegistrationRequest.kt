package ru.uoykaii.jointpurchasesecurity.api.model.request

data class RegistrationRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
)