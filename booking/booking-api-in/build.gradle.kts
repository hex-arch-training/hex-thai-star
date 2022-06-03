plugins {
    id("io.github.hex-arch-training.java-conventions")
}

dependencies {
    implementation(project(":booking-domain"))
    implementation(project(":common-domain"))
}

description = "booking-api-in"
