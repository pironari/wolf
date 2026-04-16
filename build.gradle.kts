
plugins {                                                                                                                            kotlin("jvm") version "2.3.0"
    id("com.gradleup.shadow") version "8.3.6"
}

group = "com.pironari"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7"){
        exclude("org.bukkit",module = "bukkit")
    }
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation(kotlin("stdlib"))
}

kotlin {
    jvmToolchain(21)
}

tasks {
    shadowJar {
        archiveClassifier.set("")
    }
    build {
        dependsOn(shadowJar)
    }
}