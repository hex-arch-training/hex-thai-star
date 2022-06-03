plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.spring-boot-conventions")
    id("io.github.hex-arch-training.mapstruct-conventions")
}

dependencies {
    implementation(project(":order-api-out"))
    implementation(project(":order-domain"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("com.h2database:h2:1.4.200")
}

description = "order-adapter-out-jpa"
