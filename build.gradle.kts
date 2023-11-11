plugins {
    kotlin("jvm") version "1.9.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = project.properties["maven_group"].toString()
version = project.properties["plugin_version"].toString()

repositories {
    mavenCentral()
    repositories {
        maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/") {
            name = "sonatype-oss-snapshots"
        }
    }

    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))

    //paper
    val paperVersion = "${project.properties["minecraft_version"]}-${project.properties["paper_version"]}"
    compileOnly("io.papermc.paper","paper-api", paperVersion)


    //cloud
    val cloudVersion = project.properties["cloud_version"].toString()
    implementation("cloud.commandframework","cloud-core",cloudVersion)
    implementation("cloud.commandframework","cloud-kotlin-extensions",cloudVersion)
    implementation("cloud.commandframework","cloud-paper",cloudVersion)
    implementation("cloud.commandframework","cloud-minecraft-extras",cloudVersion)
    implementation("cloud.commandframework","cloud-annotations",cloudVersion)

    //kyori
    val adventureVersion = project.properties["adventure_version"].toString()
    implementation("net.kyori","adventure-api",adventureVersion)
    implementation("net.kyori","adventure-text-serializer-plain",adventureVersion)
    implementation("net.kyori","adventure-text-serializer-legacy",adventureVersion)
    implementation("net.kyori","adventure-text-minimessage",adventureVersion)
    implementation("net.kyori","adventure-platform-bukkit", "4.3.1")

    implementation("net.kyori","adventure-text-feature-pagination","4.0.0-SNAPSHOT")

}

tasks {
    shadowJar {
        archiveClassifier = ""
    }
    build {
        dependsOn(shadowJar)
        dependsOn(processResources)
    }
    processResources {
        expand(
            "plugin_name" to project.name,
            "plugin_version" to project.version,
            "maven_group" to project.group,
            "kotlin_version" to kotlin.coreLibrariesVersion,
            "minecraft_version" to project.properties["minecraft_version"].toString(),
            "paper_version" to project.properties["paper_version"].toString(),
            "cloud_version" to project.properties["cloud_version"].toString(),
            "adventure_version" to project.properties["adventure_version"].toString(),
        )
    }
}


kotlin {
    jvmToolchain(17)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}