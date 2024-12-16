package ru.uoykaii.jointpurchasesecurity.support.mapper

import ru.uoykaii.jointpurchasesecurity.api.model.request.RegistrationRequest
import ru.uoykaii.jointpurchasesecurity.domain.model.dto.UserPrivateData
import ru.uoykaii.jointpurchasesecurity.domain.model.entity.UserAuthority
import java.util.*

fun RegistrationRequest.toUserPrivateData(uuid: UUID, password: String, salt: String) =
    UserPrivateData(
        uuid = uuid,
        email = email,
        password = password,
        salt = salt,
        firstName = firstName,
        lastName = lastName
    )
