plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.quarkus-conventions")
}

dependencies {
    implementation(project(":booking-domain"))
    implementation(project(":booking-api-out"))
    implementation("io.quarkus:quarkus-mailer")
}

description = "booking-adapter-out-quarkusmail"
