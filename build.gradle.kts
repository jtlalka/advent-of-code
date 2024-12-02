plugins {
    kotlin("jvm") version "2.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.11.3")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:2.1.0")
}

tasks {
    wrapper {
        gradleVersion = "8.7"
    }
    test {
        useJUnitPlatform()
    }
}
