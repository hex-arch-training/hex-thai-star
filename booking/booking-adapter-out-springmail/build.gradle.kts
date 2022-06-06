plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.spring-boot-conventions")
}

dependencies {
    implementation(project(":booking:booking-domain"))
    implementation(project(":booking:booking-api-out"))
    implementation("org.springframework.boot:spring-boot-starter-mail")
}

description = "booking-adapter-out-springmail"
