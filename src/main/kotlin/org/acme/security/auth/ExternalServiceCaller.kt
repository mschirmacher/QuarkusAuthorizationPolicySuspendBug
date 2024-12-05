package org.acme.security.auth

import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class ExternalServiceCaller(
    @RestClient private val externalService: ExternalService
) {
    fun validateToken(token: String): Uni<TokenInfo> {
        return externalService.getTokenInfo(token)
    }
}

@RegisterRestClient(configKey = "foo")
interface ExternalService {

    @GET
    @Path("/{token}")
    fun getTokenInfo(
        @PathParam("token") token: String
    ): Uni<TokenInfo>
}

data class TokenInfo(val isValid: Boolean)
