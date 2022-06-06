plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.mapstruct-conventions")
    id("io.github.hex-arch-training.spring-boot-conventions")
}

dependencies {
    implementation(project(":common:common-domain"))
    implementation(project(":booking:booking-domain"))
    implementation(project(":booking:booking-api-out"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation(libs.h2)
}

description = "booking-adapter-out-jpa"
