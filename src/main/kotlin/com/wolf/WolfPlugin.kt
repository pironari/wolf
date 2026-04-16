package com.wolf

import org.bukkit.plugin.java.JavaPlugin

class WolfPlugin : JavaPlugin() {

    override fun onEnable() {
        DatabaseManager.connect(
            host = "localhost",
            port = 3306,
            database = "wolfdb",
            user = "root",
            password = ""
        )
        logger.info("WolfPluginが起動しました")
    }

    override fun onDisable() {
        DatabaseManager.disconnect()
        logger.info("WolfPluginが停止しました")
    }
}