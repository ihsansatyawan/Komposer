import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
        KoinInitializerKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            // Change to MainComposeView() to demonstrate Compose in Swift (CMP)
            NewsView()
        }
    }
}
