package com.kruthers.pandalib

@Suppress("UnstableApiUsage", "MemberVisibilityCanBePrivate")
class VersionRegistry(plugin: PandaLib, properties: HashMap<String, String>) {

    val kotlin: Version
    val minecraft: Version
    val paper: String
    val cloud: Version
    val adventure: Version
    val plugin: Version

    init {
        var kotlin = "NULL"
        var minecraft = "NULL"
        var cloud = "1.8.3"
        var adventure = "NULL"
        var paper = "NULL"

        properties.forEach {
            when(it.key) {
                "kotlin_version" -> kotlin = it.value
                "cloud_version" -> cloud = it.value
                "adventure_version" -> adventure = it.value
                "paper_version" -> paper = it.value
                "minecraft_version" -> minecraft = it.value
            }
        }
        this.kotlin = Version.of(kotlin)
        this.minecraft = Version.of(minecraft)
        this.adventure = Version.of(adventure)
        this.paper = paper
        this.plugin = Version.of(plugin.pluginMeta.version)
        this.cloud = Version.of(cloud)
    }


}