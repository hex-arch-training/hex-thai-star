plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.spring-boot-conventions")
    id("io.github.hex-arch-training.jdbi-conventions")
}

dependencies {
    implementation(project(":common-application"))
    implementation(project(":booking-adapter-out-springmail"))
    implementation(project(":booking-adapter-out-jpa"))
    implementation(project(":booking-adapter-out-jdbi"))
    implementation(project(":common-adapter-out-jdbi"))
    implementation(project(":booking-adapter-in-springweb"))
    implementation(project(":booking-application"))
    implementation(project(":booking-api-in"))
    implementation(project(":booking-api-out"))
    implementation(project(":booking-domain"))
    implementation(project(":order-domain"))
    implementation(project(":order-api-in"))
    implementation(project(":order-api-out"))
    implementation(project(":order-application"))
    implementation(project(":order-adapter-out-booking"))
    implementation(project(":order-adapter-out-jpa"))
    implementation(project(":order-adapter-in-springweb"))
    implementation("org.jdbi:jdbi3-core")
    implementation("org.jdbi:jdbi3-sqlobject")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(libs.greenmail.spring)
    runtimeOnly(libs.h2)
}

description = "springboot"
