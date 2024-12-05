package org.acme

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import io.mockk.junit5.MockKExtension
import io.quarkiverse.wiremock.devservice.ConnectWireMock
import io.quarkus.test.common.http.TestHTTPEndpoint
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.QuarkusTestProfile
import io.quarkus.test.junit.TestProfile
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@QuarkusTest
@TestHTTPEndpoint(GreetingResource::class)
@ExtendWith(MockKExtension::class)
@ConnectWireMock
@TestProfile(GreetingResourceTest::class)
class GreetingResourceTest : QuarkusTestProfile {

    override fun getConfigOverrides(): Map<String, String> = (10_000..50_000).random().let { port ->
        mapOf(
            "quarkus.rest-client.foo.url" to "http://localhost:$port/",
            "quarkus.wiremock.devservices.port" to "$port",
            "quarkus.http.auth.proactive" to "true",
        )
    }

    lateinit var wireMock: WireMock

    @Test
    fun testHelloEndpoint() {

        wireMock.register(
            get(urlEqualTo("/test"))
                .willReturn(
                    aResponse().withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                            """
                                invalid json }
                            """.trimIndent()
                        )
                )
        )

        given()
            .get("name")
            .then()
            .statusCode(234)
            .body(`is`("hello name"))
    }
}
