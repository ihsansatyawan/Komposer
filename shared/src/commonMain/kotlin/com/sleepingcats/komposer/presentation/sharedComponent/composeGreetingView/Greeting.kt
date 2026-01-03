package com.sleepingcats.komposer.presentation.sharedComponent.composeGreetingView

import com.sleepingcats.komposer.tools.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}