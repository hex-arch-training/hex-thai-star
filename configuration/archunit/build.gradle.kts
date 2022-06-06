plugins {
    id("io.github.hex-arch-training.java-conventions")
}

dependencies {
    implementation(project(":common:common-domain"))
    implementation(project(":common:common-application"))
    implementation(project(":common:common-adapter-out-jdbi"))
    implementation(project(":booking:booking-domain"))
    implementation(project(":booking:booking-api-in"))
    implementation(project(":booking:booking-api-out"))
    implementation(project(":booking:booking-application"))
    implementation(project(":booking:booking-adapter-in-springweb"))
    implementation(project(":booking:booking-adapter-in-quarkusweb"))
    implementation(project(":booking:booking-adapter-in-awslambda"))
    implementation(project(":booking:booking-adapter-out-dynamodb"))
    implementation(project(":booking:booking-adapter-out-jpa"))
    implementation(project(":booking:booking-adapter-out-jdbi"))
    implementation(project(":booking:booking-adapter-out-springmail"))
    implementation(project(":booking:booking-adapter-out-quarkusmail"))
    implementation(project(":booking:booking-adapter-out-awsmail"))
    implementation(project(":order:order-domain"))
    implementation(project(":order:order-api-in"))
    implementation(project(":order:order-api-out"))
    implementation(project(":order:order-application"))
    implementation(project(":order:order-adapter-out-booking"))
    implementation(project(":order:order-adapter-out-jpa"))
    implementation(project(":order:order-adapter-in-springweb"))
    implementation(project(":configuration:springboot"))
    implementation(project(":configuration:awslambda"))
    implementation(project(":configuration:quarkus"))
    implementation(project(":configuration:quarkus-awslambda"))
    implementation(libs.guava)
    testImplementation(libs.archunit.junit5)
}

description = "archunit"
