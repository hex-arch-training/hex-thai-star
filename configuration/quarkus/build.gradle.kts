plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.quarkus-conventions")
}

dependencies {
    implementation(project(":common:common-application"))
    implementation(project(":booking:booking-api-out"))
    implementation(project(":booking:booking-api-in"))
    implementation(project(":booking:booking-application"))
    implementation(project(":booking:booking-adapter-out-quarkusmail"))
    implementation(project(":booking:booking-adapter-out-jpa"))
    implementation(project(":booking:booking-adapter-in-quarkusweb"))
    implementation(project(":booking:booking-domain"))
    implementation(project(":order:order-api-out"))
    implementation(project(":order:order-api-in"))
    implementation("io.quarkus:quarkus-spring-data-jpa")
    implementation("io.quarkus:quarkus-jdbc-h2")
}

description = "quarkus"
