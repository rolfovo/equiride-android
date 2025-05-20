// build.gradle.kts
plugins {
    id("com.android.application") version "8.1.2" apply false
    kotlin("android") version "1.9.0" apply false
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }
}
