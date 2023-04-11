plugins {
    kotlin("jvm") version "1.8.0"
}

allprojects {
    group = "com.thomaskint"
    version = "0.0.0-SNAPSHOT"

    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation(kotlin("test"))

        testImplementation("io.kotest:kotest-assertions-core:5.5.5")
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}
