plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.mapstruct-conventions")
    id("io.github.hex-arch-training.jdbi-conventions")
}

dependencies {
    implementation(project(":booking:booking-domain"))
    implementation(project(":booking:booking-api-out"))
    implementation(project(":common:common-domain"))
    implementation(project(":common:common-adapter-out-jdbi"))
}

description = "booking-adapter-out-jdbi"
