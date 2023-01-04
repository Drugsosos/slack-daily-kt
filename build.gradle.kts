import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
}

group = "slack.daily.kt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.slack.api:bolt-socket-mode:1.26.1")
    implementation("javax.websocket:javax.websocket-api:1.1")
    implementation("org.slf4j:slf4j-simple:2.0.3")
    implementation("org.glassfish.tyrus.bundles:tyrus-standalone-client:1.17")
}


tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
