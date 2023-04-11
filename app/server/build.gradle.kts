plugins {
    application
}

dependencies {
    implementation(project(":app:domain"))
    implementation(project(":app:infra"))

    implementation("io.insert-koin:koin-core:3.4.0")

    implementation(platform("org.http4k:http4k-bom:4.41.3.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-contract")
    implementation("org.http4k:http4k-format-jackson")
    implementation("org.http4k:http4k-server-undertow")
    implementation("org.http4k:http4k-cloudnative")
}

application {
    mainClass.set("MainKt")
}
