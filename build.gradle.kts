plugins {
    java
    kotlin("jvm") version "1.9.0"
    `maven-publish`
    id("org.jetbrains.dokka") version "1.9.10"
}

group = "dev.retrotv"
version = "0.14.0-alpha"

// Github Action 버전 출력용
tasks.register("printVersionName") {
    println(project.version)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("commons-codec:commons-codec:1.16.0")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
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
