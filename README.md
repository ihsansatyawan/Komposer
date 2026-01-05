# Komposer

Komposer is a Kotlin Multiplatform (KMP) starter/skeleton project configured to demonstrate a modern, multiplatform app using:
- Compose Multiplatform (CMP) for shared UI,
- Koin for dependency injection across platforms,
- Ktor for networking,
- A small example "news" feature that consumes the NewsAPI (https://newsapi.org/) to demonstrate app architecture and wiring.

This repo is intended as a starting point you can clone and adapt for your own KMP projects.

## Features
- Compose Multiplatform ready: shared Compose UI code that can run on desktop/Android and be embedded into iOS.
- Koin DI setup configured to work across platforms.
- Ktor HTTP client wired through DI.
- Example News feature demonstrating layered architecture (data / domain / presentation) and how to use Ktor + Koin.
- iOS app entrypoint uses SwiftUI by default and shows how to initialize Koin from Swift and how to switch to a Compose view.

Important: You must insert your own NewsAPI API key into NetworkModules.kt (see "Configure API key" below) before the example network calls will succeed.

## Repository layout (high level)
- composeApp/ — Android (and Compose Multiplatform UI entrypoints)
    - src/...
- iosApp/ — Xcode iOS app (Swift/SwiftUI entrypoint)
    - iosApp/ (Xcode project/workspace)
- shared/ — Kotlin Multiplatform shared code
    - src/commonMain/kotlin — shared business logic, Koin init, Ktor modules, news feature
    - src/androidMain, iosMain, jvmMain, etc. — platform specific implementations if present

(Explore the `shared` module to find the news feature, DI modules, and network code.)

## Architecture overview
The project demonstrates a simple, clean architecture style:
- Presentation: Compose UI (and example SwiftUI wrapper for iOS) that consumes view models / presenters from the shared module.
- Domain: Use-cases / business logic in shared module.
- Data: Repositories, Ktor clients, DTOs and mappers.
- DI: Koin modules (single place to configure network, repository, view models).
- Networking: Ktor client configured in a NetworkModules.kt and provided via Koin.

## Getting started

Prerequisites
- JDK 11+ (or JDK 17 recommended)
- Android Studio (with Kotlin Multiplatform and Compose support)
- Xcode (latest stable recommended) for the iOS app
- Gradle wrapper is included — do not rely on system Gradle version

Clone
```shell
git clone https://github.com/ihsansatyawan/Komposer.git
cd Komposer
```

Build Android (assemble/debug)
- macOS / Linux:
```shell
./gradlew :composeApp:assembleDebug
```
- Windows:
```shell
.\gradlew.bat :composeApp:assembleDebug
```
To install and run on a connected device/emulator:
```shell
./gradlew :composeApp:installDebug
```

Open & Run iOS
- Open the Xcode project/workspace in `iosApp/iosApp` and run on a simulator/device.
- The default iOS app uses a SwiftUI view as the default screen (see "iOS: Compose integration" below to swap to the Compose view).

## Configure API key (NewsAPI)
The example news feature uses https://newsapi.org/. You must add your API key before making requests.

1. Find `NetworkModules.kt` in the shared module. It's in the shared code base (for example under `shared/src/commonMain/kotlin/.../NetworkModules.kt`).
2. Edit the constant (or provider) that holds the API key. Look for a placeholder like:
```kotlin
const val NEWS_API_KEY = "YOUR_API_KEY_HERE"
```
or modify the Koin network module to provide your key:
```kotlin
// Example (the actual file path/name may vary)
val networkModule = module {
    single { NewsApiClient(apiKey = "YOUR_API_KEY_HERE") } // <- replace with your API key
}
```
3. Save and rebuild.

Note: The project intentionally does not include an API key. Do not commit your private API key to a public repository.

## Koin initialization
Koin is initialized in shared code and needs to be invoked on each platform at app startup.

- Android: ensure the Koin initializer is called from your Application class (onCreate). The project template may already do this for you.
- iOS: the SwiftUI App entrypoint demonstrates how to initialize Koin from Swift:

```swift
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
```

To demonstrate Compose in iOS (Compose Multiplatform embedded in SwiftUI):
- Replace `NewsView()` with `MainComposeView()` in the SwiftUI `WindowGroup` (the Compose view provided by the shared CMP code).
- Make sure the Compose view and the proper CocoaPods/build steps are configured — open the Xcode project and run.

## Notes about Compose Multiplatform on iOS
- Compose UI for iOS is available through Compose Multiplatform integration and requires proper setup in the `iosApp` target. The project includes a commented example in the iOS App file showing how to swap the default SwiftUI view with the Compose view.
- Ensure your Xcode target is properly configured (frameworks, Build Phases) to include the shared Kotlin framework.

## Testing
- Unit tests for shared code can be run via Gradle:
```shell
./gradlew :shared:test
```
- Platform-specific tests should be run from their respective environments (Android instrumentation tests from Android Studio, Xcode for iOS tests).

## Contributing
Contributions and fixes are welcome. If you plan to extend the project for production:
- Remove the example API key placeholder and provide a secure configuration strategy.
- Consider moving secrets to environment variables or a secure vault rather than committing them.
- Add feature flags, logging, and proper error handling for network failures.

## Troubleshooting
- If you see build failures related to Kotlin/Gradle versions, update your IDE Kotlin plugin or the Gradle/Kotlin versions in the Gradle files to match the repo's configuration.
- If the iOS Compose view doesn't appear or fails to build, check the generated Kotlin framework and that the iOS target has the shared framework linked.

## License
This repository is provided as-is for learning and bootstrapping Kotlin Multiplatform projects. Check the repository for a LICENSE file (or add one) before using in production.

