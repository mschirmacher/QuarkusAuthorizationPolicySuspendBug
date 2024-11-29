package org.acme

import io.mockk.junit5.MockKExtension
import io.quarkus.test.common.http.TestHTTPEndpoint
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.security.FallbackTestHttpAuthenticationMechanism
import io.quarkus.test.security.SecurityAttribute
import io.quarkus.test.security.TestSecurity
import io.quarkus.test.security.jwt.Claim
import io.quarkus.test.security.jwt.JwtSecurity
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@QuarkusTest
@TestHTTPEndpoint(GreetingResource::class)
@ExtendWith(MockKExtension::class)
class GreetingResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
            .get("name")
            .then()
            .statusCode(200)
            .body(`is`("hello name"))
    }
}
