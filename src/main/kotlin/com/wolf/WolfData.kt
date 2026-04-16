package com.wolf

import java.util.UUID

data class WolfData(
    val id: Int,
    val ownerUuid: UUID,
    val name: String,
    val level: Int,
    val exp: Int,
    val hp: Int,
    val atk: Int,
    val spd: Int,
    val status: String
)