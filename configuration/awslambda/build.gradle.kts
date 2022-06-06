plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.aws-conventions")
    id("io.github.hex-arch-training.aws-v2-conventions")
}

dependencies {
    implementation(project(":common:common-application"))
    implementation(project(":booking:booking-api-out"))
    implementation(project(":booking:booking-api-in"))
    implementation(project(":booking:booking-application"))
    implementation(project(":booking:booking-adapter-in-awslambda"))
    implementation(project(":booking:booking-adapter-out-dynamodb"))
    implementation(project(":booking:booking-adapter-out-awsmail"))
    implementation(project(":order:order-api-out"))
    implementation(project(":order:order-api-in"))
    implementation(libs.log4j.core)
    implementation(libs.log4j.api)
    implementation(libs.aws.lambda.java.core)
}

description = "awslambda"
