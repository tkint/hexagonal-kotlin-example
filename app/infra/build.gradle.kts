dependencies {
    implementation(project(":app:domain"))

    implementation("org.litote.kmongo:kmongo:4.8.0")

    implementation(platform("org.http4k:http4k-bom:4.41.3.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-contract")
    implementation("org.http4k:http4k-format-jackson")

    testImplementation("de.bwaldvogel:mongo-java-server:1.43.0")
    testImplementation(project(":app:domain", "testClasses"))
}
