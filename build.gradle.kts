import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("com.google.cloud.tools.jib") version "1.8.0"
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
}

group = "com.matthewglover"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "6.0"
    distributionType = Wrapper.DistributionType.ALL
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

jib {
    val version: String by project
    val revision: String by project
    val dockerHubPassword: String by project

    to {
        image = "matt8thlight/greet:$version-$revision"
        container {
            jvmFlags = listOf("-Xms512m", "-Xdebug")
            mainClass = "com.example.demo.DemoApplication"
            args = listOf<String>()
            ports = listOf("8080/tcp")
        }
        auth {
            username = "matt8thlight"
            password = dockerHubPassword
        }
    }
}
