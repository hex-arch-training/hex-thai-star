plugins {
    id("io.github.hex-arch-training.java-conventions")
}

dependencies {
    implementation(project(":order-api-in"))
    implementation(project(":order-api-out"))
    implementation(project(":order-domain"))
    implementation(project(":common-domain"))
    implementation(project(":common-application"))
}

description = "order-application"
