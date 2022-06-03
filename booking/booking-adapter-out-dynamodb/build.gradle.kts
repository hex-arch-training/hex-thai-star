plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.aws-v2-conventions")
}

dependencies {
    implementation("software.amazon.awssdk:dynamodb-enhanced:2.17.97")
    implementation("com.aventrix.jnanoid:jnanoid:2.0.0")
    implementation(project(":booking-api-out"))
    implementation(project(":booking-domain"))
}

description = "booking-adapter-out-dynamodb"
