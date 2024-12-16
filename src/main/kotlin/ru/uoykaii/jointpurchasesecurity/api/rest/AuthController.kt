package ru.uoykaii.jointpurchasesecurity.api.rest

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.uoykaii.jointpurchasesecurity.api.model.request.AuthenticationRequest
import ru.uoykaii.jointpurchasesecurity.api.model.request.RegistrationRequest
import ru.uoykaii.jointpurchasesecurity.api.model.response.TokenResponse
import ru.uoykaii.jointpurchasesecurity.domain.service.JwtService
import ru.uoykaii.jointpurchasesecurity.domain.service.user.UserService

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userService: UserService,
    private val jwtService: JwtService
) {

    @PostMapping("/signin")
    fun signin(@RequestBody authenticationRequest: AuthenticationRequest): TokenResponse {
        val userUuid = userService.verifyUser(authenticationRequest)
        val token = jwtService.generate(userUuid)
        return TokenResponse(token)
    }

    @PostMapping("/signup")
    fun signup(@RequestBody registrationRequest: RegistrationRequest): TokenResponse {
        val userUuid = userService.createUser(registrationRequest)
        val token = jwtService.generate(userUuid)
        return TokenResponse(token)
    }

}