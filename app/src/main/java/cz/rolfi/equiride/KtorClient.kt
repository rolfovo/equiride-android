// app/src/main/java/cz/rolfi/equiride/KtorClient.kt
package cz.rolfi.equiride

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

object KtorClient {
    val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
    const val BASE_URL = "https://tvuj-backend-url.tld"
}

// Ukázkový request na získání koní
// suspend fun getHorses(): List<HorseDto> {
//     return KtorClient.client.get("${KtorClient.BASE_URL}/horses").body()
// }
