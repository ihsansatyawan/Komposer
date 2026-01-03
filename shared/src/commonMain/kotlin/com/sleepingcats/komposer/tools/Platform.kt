package com.sleepingcats.komposer.tools

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform