package org.acme.exception

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper

@ApplicationScoped
class ExceptionMapper : ExceptionMapper<RuntimeException> {
    override fun toResponse(exception: RuntimeException?): Response =
        Response.status(Response.Status.BAD_REQUEST).entity(exception).build()
}
