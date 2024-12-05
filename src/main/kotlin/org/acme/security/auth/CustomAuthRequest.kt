package org.acme.security.auth

import io.quarkus.security.identity.request.BaseAuthenticationRequest

class CustomAuthRequest(val token: String) : BaseAuthenticationRequest()
