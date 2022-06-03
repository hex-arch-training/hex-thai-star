plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.mapstruct-conventions")
    id("io.github.hex-arch-training.jdbi-conventions")
}

dependencies {
    implementation(project(":booking-domain"))
    implementation(project(":booking-api-out"))
    implementation(project(":common-domain"))
    implementation(project(":common-adapter-out-jdbi"))
}

description = "booking-adapter-out-jdbi"
