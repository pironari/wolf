
package com.wolf

import java.util.UUID

object WolfRepository {

    fun createWolf(ownerUuid: UUID, name: String): Int {
        val sql = "INSERT INTO wolves (owner_uuid, name) VALUES (?, ?)"
        DatabaseManager.getConnection().use { conn ->
            val stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)
            stmt.setString(1, ownerUuid.toString())
            stmt.setString(2, name)
            stmt.executeUpdate()
            val keys = stmt.generatedKeys
            if (keys.next()) return keys.getInt(1)
        }
        return -1
    }

    fun getWolf(id: Int): WolfData? {
        val sql = "SELECT * FROM wolves WHERE id = ?"
        DatabaseManager.getConnection().use { conn ->
            val stmt = conn.prepareStatement(sql)
            stmt.setInt(1, id)
            val rs = stmt.executeQuery()
            if (rs.next()) {
                return WolfData(
                    id = rs.getInt("id"),
                    ownerUuid = UUID.fromString(rs.getString("owner_uuid")),
                    name = rs.getString("name"),
                    level = rs.getInt("level"),
                    exp = rs.getInt("exp"),
                    hp = rs.getInt("hp"),
                    atk = rs.getInt("atk"),
                    spd = rs.getInt("spd"),
                    status = rs.getString("status")
                )
            }
        }
        return null
    }

    fun updateName(id: Int, name: String) {
        val sql = "UPDATE wolves SET name = ? WHERE id = ?"
        DatabaseManager.getConnection().use { conn ->
            val stmt = conn.prepareStatement(sql)
            stmt.setString(1, name)
            stmt.setInt(2, id)
            stmt.executeUpdate()
        }
    }

    fun getWolfByOwner(ownerUuid: UUID): WolfData? {
        val sql = "SELECT * FROM wolves WHERE owner_uuid = ? LIMIT 1"
        DatabaseManager.getConnection().use { conn ->
            val stmt = conn.prepareStatement(sql)
            stmt.setString(1, ownerUuid.toString())
            val rs = stmt.executeQuery()
            if (rs.next()) {
                return WolfData(
                    id = rs.getInt("id"),
                    ownerUuid = UUID.fromString(rs.getString("owner_uuid")),
                    name = rs.getString("name"),
                    level = rs.getInt("level"),
                    exp = rs.getInt("exp"),
                    hp = rs.getInt("hp"),
                    atk = rs.getInt("atk"),
                    spd = rs.getInt("spd"),
                    status = rs.getString("status")
                )
            }
        }
        return null
    }
}