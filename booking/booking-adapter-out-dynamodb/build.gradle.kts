plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.aws-v2-conventions")
}

dependencies {
    implementation(project(":booking-api-out"))
    implementation(project(":booking-domain"))
    implementation(libs.dynamodb.enhanced)
    implementation(libs.jnanoid)
}

description = "booking-adapter-out-dynamodb"
