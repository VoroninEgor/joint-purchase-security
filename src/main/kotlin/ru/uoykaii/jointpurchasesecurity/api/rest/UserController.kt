package ru.uoykaii.jointpurchasesecurity.api.rest

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.uoykaii.jointpurchasesecurity.domain.model.dto.getUserDataResponse
import ru.uoykaii.jointpurchasesecurity.domain.service.JwtService
import ru.uoykaii.jointpurchasesecurity.domain.service.user.UserService
import ru.uoykaii.security.user.UserAuthority
import ru.uoykaii.security.user.UserDataResponse
import ru.uoykaii.security.user.userDataResponse
import java.util.*

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
    private val jwtService: JwtService
) {

    @GetMapping("/{token}")
    fun getUserData(@PathVariable token: String): UserDataResponse {
        logger.info { "Getting user data by jwt $token" }
        return getUserInfo(jwtService.getUserId(token))
    }

    fun getUserInfo(userId: UUID) =
        userService.getUserData(userId).let {
            userDataResponse {
                uuid = it.uuid.toString()
                email = it.email
                firstName = it.firstName
                lastName = it.lastName
                authority = UserAuthority.valueOf(it.authority.name)
            }
        }
}