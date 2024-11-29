package org.acme.auth

import io.quarkus.security.identity.request.BaseAuthenticationRequest

class CustomAuthRequest(val token: String) : BaseAuthenticationRequest()
