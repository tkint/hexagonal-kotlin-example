dependencies {
    implementation("io.konform:konform-jvm:0.4.0")
}

val testClasses by configurations.creating {
    extendsFrom(configurations.testImplementation.get())

}

tasks.create<Jar>("testJar") {
    archiveClassifier.set("test")
    from(sourceSets.test.get().output)
}

artifacts {
    add(testClasses.name, tasks["testJar"])
//    testClasses("testJar")
}
