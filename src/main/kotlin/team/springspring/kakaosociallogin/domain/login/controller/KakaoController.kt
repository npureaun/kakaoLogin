package team.springspring.kakaosociallogin.domain.login.controller

import org.springframework.http.*
import org.springframework.stereotype.Controller
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import team.springspring.kakaosociallogin.domain.login.dto.KakaoToken


@Controller
class KaKaoController(
    private val config: KaKaoInfo
) {
    @GetMapping("/oauth2/kakao/login")
    fun login(): String {
        val uri = UriComponentsBuilder
            .fromHttpUrl("https://kauth.kakao.com/oauth/authorize")
            .queryParam("response_type", "code")
            .queryParam("client_id", config.restApiKey)
            .queryParam("redirect_uri", config.redirectUri)
            .build()
            .toUriString()
        return "redirect:${uri}"
    }

    private fun getParam(code : String): MultiValueMap<String, String>{
        return LinkedMultiValueMap(mapOf(
            "grant_type" to listOf("authorization_code"),
            "client_id" to listOf(config.restApiKey),
            "redirect_uri" to listOf(config.redirectUri),
            "code" to listOf(code)
        ))
    }

    @GetMapping("/oauth2/kakao/callback")
    @ResponseBody
    fun getRedirect(@RequestParam code: String): String {
        val tokenRequest = HttpHeaders()
            .let {
                it.contentType = MediaType.APPLICATION_FORM_URLENCODED
                HttpEntity(getParam(code), it)
            }
        val tokenResponse= RestTemplate()
            .exchange(
                config.tokenUri,
                HttpMethod.POST,
                tokenRequest,
                KakaoToken::class.java
            )

        return tokenResponse.body?.accessToken ?: "Invalid Access Token"
    }
}