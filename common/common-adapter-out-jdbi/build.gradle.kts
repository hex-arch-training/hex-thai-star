plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.mapstruct-conventions")
    id("io.github.hex-arch-training.jdbi-conventions")
}

dependencies {
    implementation(project(":common-application"))
}

description = "common-adapter-out-jdbi"
