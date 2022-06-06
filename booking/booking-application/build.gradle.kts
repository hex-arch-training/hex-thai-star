plugins {
    id("io.github.hex-arch-training.java-conventions")
}

dependencies {
    implementation(project(":booking:booking-domain"))
    implementation(project(":booking:booking-api-in"))
    implementation(project(":booking:booking-api-out"))
    implementation(project(":common:common-domain"))
    implementation(project(":common:common-application"))
}

description = "booking-application"
