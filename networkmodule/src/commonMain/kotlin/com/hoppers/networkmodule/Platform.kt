package com.jk.networkmodule

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform