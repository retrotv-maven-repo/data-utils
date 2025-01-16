import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.net.URI

plugins {
    java
    jacoco
    kotlin("jvm") version "2.1.0"
    `maven-publish`
    id("org.jetbrains.dokka") version "2.0.0"
    id("org.sonarqube") version "4.0.0.2929"
}

group = "dev.retrotv"
version = "0.22.0-alpha"

// Github Action 버전 출력용
tasks.register("printVersionName") {
    description = "이 프로젝트의 버전을 출력합니다."
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    println(project.version)
}

tasks.dokkaHtml {
    outputDirectory.set(layout.buildDirectory.dir("documentation/html"))
}

repositories {
    mavenCentral()
}

val apacheCommonsText = "1.13.0"
val apacheCommonsCodec = "1.17.2"
val apacheCommonsLang = "3.17.0"
val apacheCommonsCollections = "4.5.0-M3"
val orgJson = "20250107"
val junit = "5.11.4"

dependencies {
    implementation("org.apache.commons:commons-text:${apacheCommonsText}")
    implementation("commons-codec:commons-codec:${apacheCommonsCodec}")
    implementation("org.apache.commons:commons-lang3:${apacheCommonsLang}")
    implementation("org.apache.commons:commons-collections4:${apacheCommonsCollections}")
    implementation("org.json:json:${orgJson}")
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("org.junit.jupiter:junit-jupiter-params:${junit}")
    testImplementation(kotlin("test"))
}

tasks {
    compileKotlin {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_1_8)
    }
    compileTestKotlin {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_1_8)
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = URI("https://maven.pkg.github.com/retrotv-maven-repo/data-utils")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("PASSWORD")
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            from(components["java"])
        }
    }
}

kotlin {
    jvmToolchain(8)
}

apply(from = "${rootDir}/gradle/sonarcloud.gradle")
apply(from = "${rootDir}/gradle/jacoco.gradle")
