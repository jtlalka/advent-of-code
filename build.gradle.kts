plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.6.0")
}

tasks {
    wrapper {
        gradleVersion = "7.3"
    }
    test {
        useJUnitPlatform()
    }
}
