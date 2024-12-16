package ru.uoykaii.jointpurchasesecurity.domain.service.user

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.uoykaii.jointpurchasesecurity.domain.model.dto.UserData
import ru.uoykaii.jointpurchasesecurity.domain.model.dto.UserPrivateData
import ru.uoykaii.jointpurchasesecurity.domain.model.exception.UserNotFoundException
import ru.uoykaii.jointpurchasesecurity.domain.repository.UserDataRepository
import ru.uoykaii.jointpurchasesecurity.support.mapper.toUserData
import ru.uoykaii.jointpurchasesecurity.support.mapper.toUserDataEntity
import ru.uoykaii.jointpurchasesecurity.support.mapper.toUserPrivateData
import java.util.*

@Component
@Transactional(readOnly = true)
class UserDataComponent(
    private val userDataRepository: UserDataRepository
) {

    @Transactional
    fun createUserData(userPrivateData: UserPrivateData) =
        userDataRepository.addUser(
            userPrivateData.toUserDataEntity()
        )

    fun getUserPrivateData(email: String) =
        userDataRepository.findByEmail(email)?.toUserPrivateData()
            ?: throw UserNotFoundException("User with email=$email not found")


    fun findUserDataByEmail(email: String): Boolean = userDataRepository.existsByEmail(email)

    fun getUserData(userUuid: UUID): UserData {
        val userDataEntity = userDataRepository.findByUuid(userUuid) ?: throw UserNotFoundException("User with uuid=$userUuid not found")
        return userDataEntity.toUserData()
    }
}
