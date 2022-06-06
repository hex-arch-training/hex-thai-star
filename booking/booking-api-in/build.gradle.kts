plugins {
    id("io.github.hex-arch-training.java-conventions")
}

dependencies {
    implementation(project(":booking:booking-domain"))
    implementation(project(":common:common-domain"))
}

description = "booking-api-in"
