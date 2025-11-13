plugins {
    id("java")
    id("jacoco")
    id("maven-publish")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

group = "dev.retrotv"
version = "0.24.2-alpha"

// Github Action 버전 출력용
tasks.register("printVersionName") {
    description = "이 프로젝트의 버전을 출력합니다."
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    println(project.version)
}

repositories {
    mavenCentral()
}

val apacheCommonsText = "1.13.1"
val apacheCommonsCodec = "1.18.0"
val apacheCommonsLang = "3.18.0"
val apacheCommonsCollections = "4.5.0"
val apacheCommonsValidator = "1.10.0"
val lombok = "1.18.42"
val orgJson = "20250517"
val junit = "5.13.1"

dependencies {
    implementation("org.apache.commons:commons-text:${apacheCommonsText}")
    implementation("commons-codec:commons-codec:${apacheCommonsCodec}")
    implementation("org.apache.commons:commons-lang3:${apacheCommonsLang}")
    implementation("org.apache.commons:commons-collections4:${apacheCommonsCollections}")
    implementation("commons-validator:commons-validator:${apacheCommonsValidator}")
    implementation("org.json:json:${orgJson}")

    implementation("org.projectlombok:lombok:${lombok}")

    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:${junit}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// 이 아래는 호환성 테스트를 제외한 빌드에서만 적용
buildscript {
    val isCompatibilityTest = findProperty("compatibilityTest")?.toString()?.toBoolean() ?: false
    if (!isCompatibilityTest) {
        repositories {
            gradlePluginPortal()
            mavenCentral()
        }
        dependencies {
            classpath("com.vanniktech:gradle-maven-publish-plugin:0.34.0")
            classpath("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:7.0.1.6134")
        }
    }
}

// 호환성 테스트를 제외한 빌드에서만 적용
val isCompatibilityTest = findProperty("compatibilityTest")?.toString()?.toBoolean() ?: false
if (!isCompatibilityTest) {
    apply(plugin = "com.vanniktech.maven.publish")
    apply(plugin = "org.sonarqube")
    apply(from = "${rootDir}/gradle/jacoco.gradle")
    apply(from = "${rootDir}/gradle/publish.gradle")
    apply(from = "${rootDir}/gradle/sonarcloud.gradle")
}
