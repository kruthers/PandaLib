package com.kruthers.pandalib

import java.util.Properties

@Suppress("UnstableApiUsage", "MemberVisibilityCanBePrivate")
class VersionRegistry(plugin: PandaLib, properties: Properties) {

    val kotlin: Version
    val minecraft: Version
    val paper: String
    val cloud: Version
    val adventure: Version
    val plugin: Version

    init {
        this.kotlin = Version.of(properties.getProperty("kotlin_version"))
        this.minecraft = Version.of(properties.getProperty("minecraft_version"))
        this.paper = properties.getProperty("paper_version")
        this.cloud = Version.of(properties.getProperty("cloud_version"))
        this.adventure = Version.of(properties.getProperty("adventure_version"))
        this.plugin = Version.of(plugin.pluginMeta.version)
    }


}