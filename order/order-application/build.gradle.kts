plugins {
    id("io.github.hex-arch-training.java-conventions")
}

dependencies {
    implementation(project(":order:order-api-in"))
    implementation(project(":order:order-api-out"))
    implementation(project(":order:order-domain"))
    implementation(project(":common:common-domain"))
    implementation(project(":common:common-application"))
}

description = "order-application"
