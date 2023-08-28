package com.kruthers.pandalib

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class PandaLib: JavaPlugin() {

    companion object {
        private lateinit var versions: VersionRegistry
        val versionRegistry: VersionRegistry
            get() = versions
    }

    private val properties: Properties = Properties()

    override fun onEnable() {
        this.logger.info("Loading PandaLib")

        try {
            properties.load(this.getResource(".properties"))
        } catch (err: Exception) {
            logger.severe("Failed to load plugin properties")
            Bukkit.getPluginManager().disablePlugin(this)
            return
        }

        try {
            versions = VersionRegistry(this,this.properties)
        } catch (err: Exception) {
            logger.severe("Failed to load library versions.")
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