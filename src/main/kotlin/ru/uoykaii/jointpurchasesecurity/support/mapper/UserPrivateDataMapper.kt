package ru.uoykaii.jointpurchasesecurity.support.mapper

import ru.uoykaii.jointpurchasesecurity.domain.model.dto.UserPrivateData
import ru.uoykaii.jointpurchasesecurity.domain.model.entity.UserAuthority
import ru.uoykaii.jointpurchasesecurity.domain.model.entity.UserDataEntity

fun UserPrivateData.toUserDataEntity() =
    UserDataEntity(
        uuid = uuid,
        email = email,
        password = password,
        salt = salt,
        firstName = firstName,
        lastName = lastName,
        authority = UserAuthority.USER
    )