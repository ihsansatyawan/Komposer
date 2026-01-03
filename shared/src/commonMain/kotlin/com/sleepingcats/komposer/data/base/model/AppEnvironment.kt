package com.sleepingcats.komposer.data.base.model

interface AppEnvironment {
    val baseUrl: String
    val loggingEnabled: Boolean
}