plugins {
    id("io.github.hex-arch-training.java-conventions")
}

dependencies {
    implementation(project(":order-api-out"))
    implementation(project(":booking-api-in"))
}

description = "order-adapter-out-booking"
