plugins {
    java
    kotlin("jvm") version "1.9.23"
    `maven-publish`
    id("org.jetbrains.dokka") version "1.9.20"
}

group = "dev.retrotv"
version = "0.16.0-alpha"

// Github Action 버전 출력용
tasks.register("printVersionName") {
    println(project.version)
}

tasks.dokkaHtml {
    outputDirectory.set(layout.buildDirectory.dir("documentation/html"))
}

repositories {
    mavenCentral()
}

val apacheCommonCodec = "1.17.0"
val junit = "5.10.2"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("commons-codec:commons-codec:${apacheCommonCodec}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${junit}")
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
