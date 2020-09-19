import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    val kotlinVersion = "1.3.71"

    id("org.jetbrains.kotlin.jvm").version(kotlinVersion)
    java
    `java-library`
    `maven-publish`
    id("org.springframework.boot") version "2.2.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion

    kotlin("kapt") version kotlinVersion
}

application {
    mainClassName = "com.senacor.ApplicationKt"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-web")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    val crknVersion = "3.2.20200419165537"
    implementation("io.crnk:crnk-core:$crknVersion")
    implementation("io.crnk:crnk-setup-spring-boot2:$crknVersion")
    implementation("io.github.microutils:kotlin-logging:1.8.3")
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("org.junit.vintage:junit-vintage-engine")
    }
    val kotestVersion = "4.0.5"
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")

}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    test {
        useJUnitPlatform()
    }
}

repositories {
    mavenCentral()
    jcenter()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}