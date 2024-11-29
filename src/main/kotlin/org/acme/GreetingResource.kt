@file:OptIn(ExperimentalCoroutinesApi::class)

package org.acme

import io.quarkus.security.Authenticated
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.coroutines.uni
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Path("/hello")
@Authenticated
class GreetingResource(
    private val fooService: FooService,
) {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    fun hello(@PathParam("name") name: String): Uni<String> = uni {
        fooService.doSomething(name)
    }
}
