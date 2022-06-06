plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.mapstruct-conventions")
    id("io.github.hex-arch-training.spring-boot-conventions")
}

dependencies {
    implementation(project(":common-domain"))
    implementation(project(":booking-domain"))
    implementation(project(":booking-api-out"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation(libs.h2)
}

description = "booking-adapter-out-jpa"
