package com.sleepingcats.komposer.data.core.model

import com.sleepingcats.komposer.data.base.model.AppEnvironment

class DevEnvironment: AppEnvironment {
    override val baseUrl: String
        get() = "https://newsapi.org/"
    override val loggingEnabled: Boolean
        get() = true
}

class ProdEnvironment: AppEnvironment {
    override val baseUrl: String
        get() = "https://newsapi.org/"
    override val loggingEnabled: Boolean
        get() = false
}

object DefaultAppEnvironment {
    val environment: AppEnvironment = DevEnvironment()
}