plugins {
    id("io.github.hex-arch-training.java-conventions")
}

dependencies {
    implementation(project(":order:order-api-out"))
    implementation(project(":booking:booking-api-in"))
}

description = "order-adapter-out-booking"
