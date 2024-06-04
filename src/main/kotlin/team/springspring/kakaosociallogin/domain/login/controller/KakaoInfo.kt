package team.springspring.kakaosociallogin.domain.login.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class KaKaoInfo(
    @Value("\${kakao.rest-api-key}") val restApiKey: String,
    @Value("\${kakao.redirect-uri}") val redirectUri: String,
    @Value("\${kakao.token-uri}") val tokenUri: String,
    @Value("\${kakao.user-info-uri}") val userInfoUri: String,
)
