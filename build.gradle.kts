// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
