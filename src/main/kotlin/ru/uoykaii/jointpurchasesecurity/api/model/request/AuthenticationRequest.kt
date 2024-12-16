package ru.uoykaii.jointpurchasesecurity.api.model.request

data class AuthenticationRequest(
    val email: String,
    val password: String
)
