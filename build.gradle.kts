import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    java
    jacoco
    kotlin("jvm") version "2.0.0"
    `maven-publish`
    id("org.jetbrains.dokka") version "1.9.20"
    id("org.sonarqube") version "4.0.0.2929"
}

group = "dev.retrotv"
version = "0.16.0-alpha"

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

val apacheCommonCodec = "1.17.1"
val apacheCommonLang = "3.16.0"
val apacheCommonCollections = "4.5.0-M2"
val junit = "5.10.2"

dependencies {
    implementation("commons-codec:commons-codec:${apacheCommonCodec}")
    implementation("org.apache.commons:commons-lang3:${apacheCommonLang}")
    implementation("org.apache.commons:commons-collections4:${apacheCommonCollections}")
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
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "data-utils"
            version = project.version.toString()

            from(components["java"])
        }
    }
}

kotlin {
    jvmToolchain(8)
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

tasks.jacocoTestReport {
    reports {

        // HTML 파일을 생성하도록 설정
        html.required = true

        // SonarQube에서 Jacoco XML 파일을 읽을 수 있도록 설정
        xml.required = true
        csv.required = false
    }
}

sonar {
    properties {
        property("sonar.projectKey", "retrotv-maven-repo_data-utils")
        property("sonar.organization", "retrotv-maven-repo")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.exclusions", "**/enums/*")
    }
}
