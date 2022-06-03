plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.aws-conventions")
    id("io.github.hex-arch-training.spring-boot-conventions")
}

dependencies {
    implementation(project(":booking-domain"))
    implementation(project(":booking-api-out"))
    implementation("com.amazonaws:aws-java-sdk-ses")
}

description = "booking-adapter-out-awsmail"
