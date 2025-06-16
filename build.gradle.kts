import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.net.URI

plugins {
    java
    jacoco
    `maven-publish`
    kotlin("jvm") version "2.1.21"
    id("com.vanniktech.maven.publish") version "0.32.0"
    id("org.jetbrains.dokka") version "2.0.0"
    id("org.sonarqube") version "4.0.0.2929"
}

group = "dev.retrotv"
version = "0.23.0-alpha"

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

val apacheCommonsText = "1.13.1"
val apacheCommonsCodec = "1.18.0"
val apacheCommonsLang = "3.17.0"
val apacheCommonsCollections = "4.5.0"
val orgJson = "20250517"
val junit = "5.13.1"

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

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), project.name, version.toString())

    pom {
        name.set("data-utils")
        description.set("Java 자료형과 관련된 유틸성 기능을 총망라한 라이브러리 입니다.")
        inceptionYear.set("2025")
        url.set("https://github.com/retrotv-maven-repo/data-utils")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("yjj8353")
                name.set("JaeJun Yang")
                email.set("yjj8353@gmail.com")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/retrotv-maven-repo/data-utils.git")
            developerConnection.set("scm:git:ssh://github.com/retrotv-maven-repo/data-utils.git")
            url.set("https://github.com/retrotv-maven-repo/data-utils.git")
        }
    }

    publishing {
        repositories {

            // Github Packages에 배포하기 위한 설정
            maven {
                name = "GitHubPackages"
                url = URI("https://maven.pkg.github.com/retrotv-maven-repo/data-utils")
                credentials {
                    username = System.getenv("USERNAME")
                    password = System.getenv("PASSWORD")
                }
            }
        }
    }
}

tasks.withType<Sign>().configureEach {
    onlyIf {

        // 로컬 및 깃허브 패키지 배포 시에는 서명하지 않도록 설정
        !gradle.taskGraph.hasTask(":publishMavenPublicationToMavenLocal") && !gradle.taskGraph.hasTask(":publishMavenPublicationToGitHubPackagesRepository")
    }
}

kotlin {
    jvmToolchain(8)
}

apply(from = "${rootDir}/gradle/sonarcloud.gradle")
apply(from = "${rootDir}/gradle/jacoco.gradle")
