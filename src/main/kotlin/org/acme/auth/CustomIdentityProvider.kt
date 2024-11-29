package org.acme.auth

import io.quarkus.security.credential.TokenCredential
import io.quarkus.security.identity.AuthenticationRequestContext
import io.quarkus.security.identity.IdentityProvider
import io.quarkus.security.identity.SecurityIdentity
import io.quarkus.security.runtime.QuarkusSecurityIdentity
import io.quarkus.vertx.http.runtime.security.HttpSecurityUtils
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.uni
import io.vertx.ext.web.RoutingContext
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CustomIdentityProvider :
    IdentityProvider<CustomAuthRequest> {
    override fun getRequestType(): Class<CustomAuthRequest> =
        CustomAuthRequest::class.java

    override fun authenticate(
        request: CustomAuthRequest,
        context: AuthenticationRequestContext
    ): Uni<SecurityIdentity> {
        val builder = QuarkusSecurityIdentity.builder()
            .setPrincipal({ "Name" })
            .addRoles(setOf("some", "roles"))
            .addAttribute("userId", "some userid")
            .addAttribute("userName", "userName")
            .addAttribute("isAdmin", false)
            .addCredential(
                TokenCredential(
                    request.token,
                    "bearer"
                )
            )
            .setAnonymous(false)
        val routingContext =
            HttpSecurityUtils.getRoutingContextAttribute(
                request
            )

        if (routingContext != null) {
            builder.addAttribute(
                RoutingContext::class.java.getName(),
                routingContext
            )
        }
        return uni { builder.build() }
    }
}
