<p align="center">
  <img src="logo.png" height="200px"  alt="project logo"/>
</p>

## Project structure

* `/composeApp` is for code that will be shared across Compose Multiplatform applications.
  Sub-folders:
    - `/commonMain` is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the
      folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      `/iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for
  your project

## Versions

- Gradle: 8.7
- Android Studio: Jellyfish 2023.3.1
- Dev platform OS: Win10 22H2 19045.4291
