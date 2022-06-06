plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.quarkus-conventions")
}

dependencies {
    implementation(project(":booking:booking-domain"))
    implementation(project(":booking:booking-api-in"))
    implementation("io.quarkus:quarkus-resteasy-jackson")
}

description = "booking-adapter-in-quarkusweb"
