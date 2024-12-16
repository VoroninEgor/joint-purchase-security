package ru.uoykaii.jointpurchasesecurity.api.grpc

import io.github.oshai.kotlinlogging.KotlinLogging
import net.devh.boot.grpc.server.service.GrpcService
import ru.uoykaii.jointpurchasesecurity.domain.service.JwtService
import ru.uoykaii.jointpurchasesecurity.domain.service.user.UserService
import ru.uoykaii.security.user.GetUserDataJwtRequest
import ru.uoykaii.security.user.UserAuthority
import ru.uoykaii.security.user.UserDataResponse
import ru.uoykaii.security.user.UserDataServiceGrpcKt.UserDataServiceCoroutineImplBase
import ru.uoykaii.security.user.userDataResponse
import java.util.*

private val logger = KotlinLogging.logger {}

@GrpcService
class UserDataGrpcService(
    private val userService: UserService,
    private val jwtService: JwtService
) : UserDataServiceCoroutineImplBase() {

    override suspend fun getUserDataByJwt(request: GetUserDataJwtRequest) : UserDataResponse {
        logger.info { "Getting user data by jwt ${request.jwt}" }
        return getUserInfo(jwtService.getUserId(request.jwt))
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
