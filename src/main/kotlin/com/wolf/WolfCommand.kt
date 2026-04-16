package com.wolf

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Wolf

class WolfCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("プレイヤーのみ使用できます")
            return true
        }

        when (args.getOrNull(0)) {
            "create" -> {
                val name = args.getOrNull(1) ?: run {
                    sender.sendMessage("/wolf create [名前] で名前を指定してください")
                    return true
                }
                if (name.length > 10) {
                    sender.sendMessage("名前は10文字以内にしてください")
                    return true
                }
                val id = WolfRepository.createWolf(sender.uniqueId, name)
                val wolf = sender.world.spawnEntity(sender.location, EntityType.WOLF) as Wolf
                wolf.isTamed = true
                wolf.owner = sender
                wolf.customName = name
                wolf.isCustomNameVisible = true
                sender.sendMessage("${name} を召喚しました！(ID: $id)")
            }
            "info" -> {
                val wolf = WolfRepository.getWolfByOwner(sender.uniqueId)
                if (wolf == null) {
                    sender.sendMessage("オオカミがいません。/wolf create [名前] で作成してください")
                    return true
                }
                sender.sendMessage("=== ${wolf.name} ===")
                sender.sendMessage("Lv: ${wolf.level} | HP: ${wolf.hp} | ATK: ${wolf.atk} | SPD: ${wolf.spd}")
            }
            else -> sender.sendMessage("/wolf <create|info>")
        }
        return true
    }
}
