# Project structure

```
project/
├── composeApp/
│   ├── commonMain/
│   ├── iosMain/
│   ├── desktopMain/
│   └── androidMain/
└── iosApp/
```

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

```mermaid
%%{
  init: {
    'theme': 'neutral',
    'flowchart': { 
        'curve': 'natural',
    },
  }
}%%

flowchart TB
  subgraph composeApp
    AndroidMain(androidMain)
    Common(commonMain)
    iosMain(iosMain)
  end

  style composeApp fill:transparent, 

  AndroidMain(androidMain) --> NativeAndroid(Android Library)
  Common(commonMain) --> NativeAndroid(Android Library)

  Common(commonMain) --> iOSFramework(iOS framework)
  iosMain(iosMain) --> iOSFramework(iOS framework)
```

# Versions

- Gradle: 8.7
- Android Studio: Jellyfish 2023.3.1
- Dev platform OS: Win10 22H2 19045.4291

# Local conventions

- Singular nouns in package names
- While project in active development state, unstructured fat-ass commits are allowed
- To keep aligned on the functional aspect of Jetpack Compose, the best writing approach is to
  inject instances directly into functions properties. This way allow to have default implementation
  with Koin, but keep open to inject different instances
- Nothing worse imo than create "util" folder with bunch of general shit. That's better to honestly
  say "idk wft located here but I need this", so unless project structure is forming, "wtf" packages
  exists :(
- Prefer named arguments on function call

# Dependencies overview

[Versions catalog](/gradle/libs.versions.toml)

- Settings multiplatform
    - I found more examples with that, later probably better to use data store
- Voyager
- Koin
- SQLDelight
- Lyricist
- Napier
- Ktor

# Supporting Backend Project

[The backend that powers this app](https://github.com/Smart-Health-LLC/server)
