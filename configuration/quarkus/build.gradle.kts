plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.quarkus-conventions")
}

dependencies {
    implementation(project(":common-application"))
    implementation(project(":booking-api-out"))
    implementation(project(":booking-api-in"))
    implementation(project(":booking-application"))
    implementation(project(":booking-adapter-out-quarkusmail"))
    implementation(project(":booking-adapter-out-jpa"))
    implementation(project(":booking-adapter-in-quarkusweb"))
    implementation(project(":booking-domain"))
    implementation(project(":order-api-out"))
    implementation(project(":order-api-in"))
    implementation("io.quarkus:quarkus-spring-data-jpa")
    implementation("io.quarkus:quarkus-jdbc-h2")
}

description = "quarkus"
