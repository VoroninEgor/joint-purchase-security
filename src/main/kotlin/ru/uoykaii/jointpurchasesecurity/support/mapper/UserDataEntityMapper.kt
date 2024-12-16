package ru.uoykaii.jointpurchasesecurity.support.mapper

import ru.uoykaii.jointpurchasesecurity.domain.model.dto.UserData
import ru.uoykaii.jointpurchasesecurity.domain.model.dto.UserPrivateData
import ru.uoykaii.jointpurchasesecurity.domain.model.entity.UserDataEntity

fun UserDataEntity.toUserData() = UserData(
    uuid = uuid,
    email = email,
    firstName = firstName,
    lastName = lastName,
    authority = authority
)

fun UserDataEntity.toUserPrivateData() = UserPrivateData(
    uuid = uuid,
    email = email,
    password = password,
    salt = salt,
    firstName = firstName,
    lastName = lastName
)