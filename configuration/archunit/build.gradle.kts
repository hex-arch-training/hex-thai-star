plugins {
    id("io.github.hex-arch-training.java-conventions")
}

dependencies {
    implementation(project(":common-domain"))
    implementation(project(":common-application"))
    implementation(project(":common-adapter-out-jdbi"))
    implementation(project(":booking-domain"))
    implementation(project(":booking-api-in"))
    implementation(project(":booking-api-out"))
    implementation(project(":booking-application"))
    implementation(project(":booking-adapter-in-springweb"))
    implementation(project(":booking-adapter-in-quarkusweb"))
    implementation(project(":booking-adapter-in-awslambda"))
    implementation(project(":booking-adapter-out-dynamodb"))
    implementation(project(":booking-adapter-out-jpa"))
    implementation(project(":booking-adapter-out-jdbi"))
    implementation(project(":booking-adapter-out-springmail"))
    implementation(project(":booking-adapter-out-quarkusmail"))
    implementation(project(":booking-adapter-out-awsmail"))
    implementation(project(":order-domain"))
    implementation(project(":order-api-in"))
    implementation(project(":order-api-out"))
    implementation(project(":order-application"))
    implementation(project(":order-adapter-out-booking"))
    implementation(project(":order-adapter-out-jpa"))
    implementation(project(":order-adapter-in-springweb"))
    implementation(project(":springboot"))
    implementation(project(":awslambda"))
    implementation(project(":quarkus"))
    implementation(project(":quarkus-awslambda"))
    implementation(libs.guava)
    testImplementation(libs.archunit.junit5)
}

description = "archunit"
