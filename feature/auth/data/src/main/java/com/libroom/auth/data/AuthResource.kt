package com.libroom.auth.data

import io.ktor.resources.Resource

@Resource("auth")
internal class AuthResource {
    @Resource("login")
    class Login(val parent: AuthResource = AuthResource())
}