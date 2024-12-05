package org.acme.exception

import com.fasterxml.jackson.core.JsonParseException
import jakarta.annotation.Priority
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.core.Response
import org.jboss.resteasy.reactive.ClientWebApplicationException
import org.jboss.resteasy.reactive.server.ServerExceptionMapper

@ApplicationScoped
@Priority(1)
class ServerExceptionMapper {

    @ServerExceptionMapper
    fun handle(e: JsonParseException): Response =
        Response.status(234).build()

    @ServerExceptionMapper
    fun handle(e: ClientWebApplicationException): Response =
        Response.status(234).build()
}
