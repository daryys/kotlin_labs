plugins {
    kotlin("jvm") version "1.8.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    compileOnly("org.apache.spark:spark-sql_2.13:3.3.2")
    implementation("org.jetbrains.kotlinx.spark:kotlin-spark-api_3.3.2_2.13:1.2.4")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}