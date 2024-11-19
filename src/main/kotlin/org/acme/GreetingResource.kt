package org.acme

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import io.quarkus.vertx.http.security.AuthorizationPolicy

@Path("/hello")
class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @AuthorizationPolicy(name = "foo")
    suspend fun hello() = "Hello from Quarkus REST"
}
