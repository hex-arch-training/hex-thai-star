plugins {
    id("io.github.hex-arch-training.java-conventions")
    id("io.github.hex-arch-training.spring-boot-conventions")
}

dependencies {
    implementation(project(":order-api-in"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}

description = "order-adapter-in-springweb"
