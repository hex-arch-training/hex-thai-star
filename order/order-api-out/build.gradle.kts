plugins {
    id("io.github.hex-arch-training.java-conventions")
}

dependencies {
    implementation(project(":order-domain"))
    implementation(project(":common-application"))
}

description = "order-api-out"
