package org.acme

import io.quarkus.security.identity.SecurityIdentity
import io.quarkus.vertx.http.runtime.security.HttpSecurityPolicy
import io.smallrye.mutiny.Uni
import io.vertx.ext.web.RoutingContext
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FooPolicy : HttpSecurityPolicy {
    override fun checkPermission(request: RoutingContext?, identity: Uni<SecurityIdentity>?, requestContext: HttpSecurityPolicy.AuthorizationRequestContext?): Uni<HttpSecurityPolicy.CheckResult> {
        TODO("Not yet implemented")
    }

    override fun name() = "foo"
}
