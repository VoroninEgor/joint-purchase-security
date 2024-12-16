package ru.uoykaii.jointpurchasesecurity.domain.service.encryption

import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class EncryptionService {
    fun encrypt(payload: String, salt: String): String = BCrypt.hashpw(payload, salt)

    fun generateSalt(): String = BCrypt.gensalt()
}
