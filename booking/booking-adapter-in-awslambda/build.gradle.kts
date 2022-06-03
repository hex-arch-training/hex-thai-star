plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.aws-conventions")
    id("io.github.hex-arch-training.spring-boot-conventions")
}

dependencies {
    implementation(project(":booking-domain"))
    implementation(project(":booking-api-in"))
    implementation("com.amazonaws:aws-lambda-java-log4j2:1.1.0")
    implementation("org.apache.logging.log4j:log4j-core:2.8.2")
    implementation("org.apache.logging.log4j:log4j-api:2.8.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.13.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.1")
    testImplementation("org.skyscreamer:jsonassert:1.5.0")
}

description = "booking-adapter-in-awslambda"
