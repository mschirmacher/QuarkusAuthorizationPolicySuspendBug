package org.acme

import io.quarkus.security.identity.CurrentIdentityAssociation
import io.smallrye.mutiny.coroutines.awaitSuspending
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.ForbiddenException

@ApplicationScoped
class FooService(
    private val identities: CurrentIdentityAssociation,
) {
    suspend fun doSomething(input: String): String {
        val identity = identities.deferredIdentity.awaitSuspending()

        return if (identity.isAnonymous)
            throw ForbiddenException("nope")
        else
            "hello $input"
    }
}
