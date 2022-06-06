plugins {
    id("io.github.hex-arch-training.java-conventions")
}

dependencies {
    implementation(project(":order:order-domain"))
    implementation(project(":common:common-application"))
}

description = "order-api-out"
