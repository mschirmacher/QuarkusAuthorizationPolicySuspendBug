package org.acme.security.auth

import io.smallrye.mutiny.Uni
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "foo")
interface ExternalServiceClient {

    @GET
    @Path("/{token}")
    fun getTokenInfo(
        @PathParam("token") token: String
    ): Uni<TokenInfo>
}

data class TokenInfo(val isValid: Boolean)
