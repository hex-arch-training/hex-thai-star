plugins {
    id("io.github.hex-arch-training.java-conventions")
}

dependencies {
    implementation(project(":booking-domain"))
    implementation(project(":booking-api-in"))
    implementation(project(":booking-api-out"))
    implementation(project(":common-domain"))
    implementation(project(":common-application"))
}

description = "booking-application"
