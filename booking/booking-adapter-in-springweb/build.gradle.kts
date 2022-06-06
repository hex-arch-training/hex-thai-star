plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.spring-boot-conventions")
}

dependencies {
    implementation(project(":booking:booking-domain"))
    implementation(project(":booking:booking-api-in"))
    implementation(project(":common:common-domain"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}

description = "booking-adapter-in-springweb"
