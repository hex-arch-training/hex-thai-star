plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.spring-boot-conventions")
    id("io.github.hex-arch-training.jdbi-conventions")
}

dependencies {
    implementation(project(":common:common-application"))
    implementation(project(":booking:booking-adapter-out-springmail"))
    implementation(project(":booking:booking-adapter-out-jpa"))
    implementation(project(":booking:booking-adapter-out-jdbi"))
    implementation(project(":common:common-adapter-out-jdbi"))
    implementation(project(":booking:booking-adapter-in-springweb"))
    implementation(project(":booking:booking-application"))
    implementation(project(":booking:booking-api-in"))
    implementation(project(":booking:booking-api-out"))
    implementation(project(":booking:booking-domain"))
    implementation(project(":order:order-domain"))
    implementation(project(":order:order-api-in"))
    implementation(project(":order:order-api-out"))
    implementation(project(":order:order-application"))
    implementation(project(":order:order-adapter-out-booking"))
    implementation(project(":order:order-adapter-out-jpa"))
    implementation(project(":order:order-adapter-in-springweb"))
    implementation("org.jdbi:jdbi3-core")
    implementation("org.jdbi:jdbi3-sqlobject")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(libs.greenmail.spring)
    runtimeOnly(libs.h2)
}

description = "springboot"
