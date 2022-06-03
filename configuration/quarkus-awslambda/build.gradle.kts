plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.quarkus-conventions")
    id("io.github.hex-arch-training.aws-conventions")
    id("io.github.hex-arch-training.aws-v2-conventions")
}

dependencies {
    implementation(project(":common-application"))
    implementation(project(":booking-application"))
    implementation(project(":booking-api-out"))
    implementation(project(":booking-api-in"))
    implementation(project(":booking-domain"))
    implementation(project(":booking-adapter-in-quarkusweb"))
    implementation(project(":booking-adapter-out-quarkusmail"))
    implementation(project(":booking-adapter-out-dynamodb"))
    implementation("io.quarkus:quarkus-amazon-dynamodb")
    implementation("software.amazon.awssdk:url-connection-client")
    implementation("software.amazon.awssdk:netty-nio-client")
    implementation("io.quarkus:quarkus-amazon-lambda-http")
    implementation("io.quarkus:quarkus-security")
}

description = "quarkus-awslambda"
