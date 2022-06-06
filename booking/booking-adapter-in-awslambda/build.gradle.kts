plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.aws-conventions")
    id("io.github.hex-arch-training.spring-boot-conventions")
}

dependencies {
    implementation(project(":booking-domain"))
    implementation(project(":booking-api-in"))
    implementation(libs.aws.lambda.java.log4j2)
    implementation(libs.log4j.core)
    implementation(libs.log4j.api)
    implementation(libs.jackson.core)
    implementation(libs.jackson.databind)
    implementation(libs.jackson.annotations)
    implementation(libs.jackson.datatype.jsr310)
    testImplementation(libs.jsonassert)
}

description = "booking-adapter-in-awslambda"
