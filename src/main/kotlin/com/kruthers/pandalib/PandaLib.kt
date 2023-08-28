package com.kruthers.pandalib

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.collections.HashMap

class PandaLib: JavaPlugin() {

    companion object {
        private lateinit var versions: VersionRegistry
        val versionRegistry: VersionRegistry
            get() = versions
    }

    override fun onEnable() {
        this.logger.info("Loading PandaLib")

        val properties: HashMap<String, String> = hashMapOf()
        Properties().also {
            it.load(this.classLoader.getResourceAsStream(".properties"))
            it.forEach { (key, value) -> properties["$key"] = "$value" }
        }

        try {
            versions = VersionRegistry(this, properties)
        } catch (err: Exception) {
            logger.severe("Failed to load library versions: $err")
            Bukkit.getPluginManager().disablePlugin(this)
            return
        }

        this.logger.info("Loaded PandaLib version ${versionRegistry.plugin}, supporting libs:")
        this.logger.info("Cloud: ${versionRegistry.cloud}")
        this.logger.info("Adventure: ${versionRegistry.adventure}")
    }

    override fun onDisable() {
        this.logger.info("Good bye world o/")
    }

}