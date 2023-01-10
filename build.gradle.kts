import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion by System.getProperties()
    kotlin("jvm") version "$kotlinVersion"
    id("org.jetbrains.kotlin.plugin.jpa") version "$kotlinVersion"
    application
}

group = "slack.daily.kt"
version = "1.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.clojars.org")
        name = "Clojars"
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.slack.api:bolt-socket-mode:1.27.2")
    implementation("javax.websocket:javax.websocket-api:1.1")
    implementation("org.slf4j:slf4j-simple:2.0.5")
    implementation("org.glassfish.tyrus.bundles:tyrus-standalone-client:2.1.0")
    implementation("org.hibernate:hibernate-core:6.1.6.Final")
    implementation("org.glassfish.jaxb:jaxb-runtime:4.0.0")
    implementation("org.postgresql:postgresql:42.5.1")
    implementation ("io.github.microutils:kotlin-logging-jvm:3.0.4")
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
