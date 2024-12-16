package ru.uoykaii.jointpurchasesecurity.domain.service.user

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import ru.uoykaii.jointpurchasesecurity.api.model.request.AuthenticationRequest
import ru.uoykaii.jointpurchasesecurity.api.model.request.RegistrationRequest
import ru.uoykaii.jointpurchasesecurity.domain.model.exception.UserAlreadyExistsException
import ru.uoykaii.jointpurchasesecurity.domain.model.exception.WrongPasswordException
import ru.uoykaii.jointpurchasesecurity.domain.service.encryption.EncryptionService
import ru.uoykaii.jointpurchasesecurity.support.mapper.toUserPrivateData
import java.util.UUID

private val logger = KotlinLogging.logger {}

@Service
class UserService(
    private val userDataComponent: UserDataComponent,
    private val encryptionService: EncryptionService
) {
    fun createUser(request: RegistrationRequest): UUID {
        logger.info { "Start creating user, email=${request.email}" }

        if (userExistsByEmail(request.email)) {
            throw UserAlreadyExistsException("User with email ${request.email} already exists")
        }

        val uuid = UUID.randomUUID()
        val passwordSalt = encryptionService.generateSalt()
        val hashedPassword = encryptionService.encrypt(request.password, passwordSalt)

        request.toUserPrivateData(uuid, hashedPassword, passwordSalt).apply {
            userDataComponent.createUserData(this)
        }

        logger.info { "End of creating user, email=${request.email}" }
        return uuid
    }

    fun verifyUser(request: AuthenticationRequest): UUID {
        logger.info { "Start verifying user, email=${request.email}" }

        val userPrivateData = userDataComponent.getUserPrivateData(request.email)
        val hashedPassword = encryptionService.encrypt(request.password, userPrivateData.salt)
        if (hashedPassword != userPrivateData.password) {
            throw WrongPasswordException("Password is incorrect")
        }

        logger.info { "End of verifying user, email=${request.email}" }

        return userPrivateData.uuid
    }

    fun userExistsByEmail(email: String): Boolean = userDataComponent.findUserDataByEmail(email)

    fun getUserData(userUuid: UUID) = userDataComponent.getUserData(userUuid)
}
