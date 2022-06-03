plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.aws-conventions")
    id("io.github.hex-arch-training.aws-v2-conventions")
}

dependencies {
    implementation(project(":common-application"))
    implementation(project(":booking-api-out"))
    implementation(project(":booking-api-in"))
    implementation(project(":booking-application"))
    implementation(project(":booking-adapter-in-awslambda"))
    implementation(project(":booking-adapter-out-dynamodb"))
    implementation(project(":booking-adapter-out-awsmail"))
    implementation(project(":order-api-out"))
    implementation(project(":order-api-in"))
    implementation("org.apache.logging.log4j:log4j-core:2.8.2")
    implementation("org.apache.logging.log4j:log4j-api:2.8.2")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.1")
}

description = "awslambda"
