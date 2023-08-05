package org.delivery.account.domain.token.service

import lombok.RequiredArgsConstructor
import org.delivery.account.domain.token.ifs.TokenHelperIfs
import org.delivery.account.domain.token.model.TokenDto
import org.delivery.commom.error.ErrorCode
import org.delivery.commom.exception.ApiException
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val tokenHelperIfs: TokenHelperIfs
) {

    fun issueAccessToken(userId: Long?): TokenDto? {
        return userId?.let {
            val data = mapOf("userId" to it)
            tokenHelperIfs.issueAccessToken(data)
        }
    }

    fun issueRefreshToken(userId: Long?): TokenDto? {
        requireNotNull(userId)

        val data = mapOf("userId" to userId)
        return tokenHelperIfs.issueRefreshToken(data)
    }

    fun validationToken(token: String?): Long?{

        return token?.let {
            tokenHelperIfs.validationTokenWithThrow(token)
        }?.let {map ->
            map["userId"]
        }?.let {userId ->
            userId.toString().toLong()
        }

        /*
        requireNotNull(token)

        val map = tokenHelperIfs.validationTokenWithThrow(token)

        val userId = map?.get("userId")

        requireNotNull(userId)

        return "$userId".toLong()
        */
    }
}