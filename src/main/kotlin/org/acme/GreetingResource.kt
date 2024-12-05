@file:OptIn(ExperimentalCoroutinesApi::class)

package org.acme

import io.quarkus.security.Authenticated
import io.quarkus.smallrye.reactivemessaging.runtime.kotlin.VertxDispatcher
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.coroutines.uni
import io.vertx.core.Vertx
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext

@Path("/hello")
@Authenticated
class GreetingResource(
    private val fooService: FooService,
) {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    fun hello(@PathParam("name") name: String): Uni<String> {

        val dispatcher: CoroutineDispatcher =
            Vertx.currentContext()?.let { VertxDispatcher(it) }
                ?: throw IllegalStateException("No Vertx context found")

        return uni {
            withContext(dispatcher) {
                fooService.doSomething(name)
            }
        }
    }
}
