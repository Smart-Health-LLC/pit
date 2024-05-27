import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.sqldelight)
}
ksp {
    arg("lyricist.internalVisibility", "true")
    arg("lyricist.generateStringsProperty", "true")
}
dependencies {
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.ui.graphics.android)
    implementation(libs.androidx.ui.android)
    add("kspCommonMainMetadata", libs.lyricist.processor)
}

// workaround for KSP only in Common Main.
// https://github.com/google/ksp/issues/567
tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

kotlin.sourceSets.commonMain {
    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    // https://stackoverflow.com/questions/36465824/android-studio-task-testclasses-not-found-in-project#50550818
    task("testClasses").doLast {
        println("This is a dummy testClasses task")
    }

    jvm("desktop")

//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach { iosTarget ->
//        iosTarget.binaries.framework {
//            baseName = "ComposeApp"
//            isStatic = true
//        }
//    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.android)
            implementation(libs.sqldelight.android)
        }
        commonMain.dependencies {

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)

            // ==== toast ====
            implementation(libs.sonner)

            // ==== sql delight ====
            implementation(libs.sqldelight.coroutines)

            // ==== i18n ====
            implementation(libs.lyricist)
            implementation(libs.lyricist.processor)


            // ==== Ktor ====
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.negotiation)
            implementation(libs.kotlinx.serialization.json)


            // ==== Compose ====
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.material3)
            implementation(libs.material3.window.size.multiplatform)
//            implementation(libs.compose.navigation)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.components.resources)


            // ==== Koin ====
            implementation(libs.koin)
            api(libs.koin.compose)


            // ==== Logging ====
            api(libs.napier)


            // ==== Jetpack Viewmodel ====
            // implementation(libs.androidx.lifecycle.compose)
            // implementation(libs.androidx.lifecycle.compose)


            // ==== Navigation ====
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.bottomSheetNavigator)
            implementation(libs.voyager.transitions)
            implementation(libs.voyager.tabNavigator)
            implementation(libs.voyager.koin)


            // === Simple key-value storage
            api(libs.multiplatformSettings.noArg)
            api(libs.multiplatformSettings.coroutines)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.sqldelight.jvm)
        }
//        iosMain.dependencies {
//            implementation(libs.sqldelight.native)
//            implementation(libs.ktor.client.darwin)
//        }
    }
}

android {
    namespace = "com.smarthealth.pit"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.smarthealth.pit"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
        implementation(libs.koin.android)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.smarthealth.pit"
            packageVersion = "1.0.0"
        }
    }
}

sqldelight {
    databases {
        create("PitDatabase") {
            packageName.set("com.smarthealth.pit.database")
        }
    }
}