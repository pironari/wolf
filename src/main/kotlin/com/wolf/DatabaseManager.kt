package com.wolf

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

object DatabaseManager {

    private lateinit var dataSource: HikariDataSource

    fun connect(host: String, port: Int, database: String, user: String, password: String) {
        val config = HikariConfig()
        config.jdbcUrl = "jdbc:mysql://$host:$port/$database"
        config.username = user
        config.password = password
        config.maximumPoolSize = 5
        dataSource = HikariDataSource(config)
    }

    fun disconnect() {
        if (::dataSource.isInitialized) {
            dataSource.close()
        }
    }

    fun getConnection() = dataSource.getConnection()
}

