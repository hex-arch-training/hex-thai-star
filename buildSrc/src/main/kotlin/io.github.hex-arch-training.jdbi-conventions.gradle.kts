plugins {
    `java-library`
}

dependencies {
    implementation(platform("org.jdbi:jdbi3-bom:3.24.1"))
    implementation("org.jdbi:jdbi3-core")
    implementation("org.jdbi:jdbi3-sqlobject")
    testImplementation("com.h2database:h2:1.4.200")
    testImplementation("org.jdbi:jdbi3-testing")
}
