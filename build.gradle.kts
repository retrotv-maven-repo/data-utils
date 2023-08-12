plugins {
    java
    kotlin("jvm") version "1.9.0"
    `maven-publish`
    id("org.jetbrains.dokka") version "1.8.20"
}

group = "dev.retrotv"
version = "0.6.0-alpha"

// Github Action 버전 출력용
tasks.register("printVersionName") {
    println(project.version)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "data-utils"
            version = project.version.toString()

            from(components["java"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}
