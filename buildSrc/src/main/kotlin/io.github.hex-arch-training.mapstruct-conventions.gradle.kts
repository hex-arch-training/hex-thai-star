import gradle.kotlin.dsl.accessors._2b722e86ca43fdb41c2b3d5e9b035370.annotationProcessor
import gradle.kotlin.dsl.accessors._2b722e86ca43fdb41c2b3d5e9b035370.implementation

plugins {
    `java-library`
}

dependencies {
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    annotationProcessor ("org.mapstruct:mapstruct-processor:1.4.2.Final")
}
