package team.springspring.kakaosociallogin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@SpringBootApplication
class KakaoSocialLoginApplication

fun main(args: Array<String>) {
    runApplication<KakaoSocialLoginApplication>(*args)
}
