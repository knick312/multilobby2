// Este plugin está licenciado bajo la Licencia Extendida de Código Abierto con Restricción Comercial.
// Consulta el archivo LICENSE-MULTILOBBY.txt incluido en este proyecto para más información.

/*
 * MultiLobby - Copyright (c) 2025 Flores Nicolas Eugenio
 *
 * Este software se proporciona bajo una Licencia de Código Abierto con Restricción Comercial.
 * Permitido: uso personal, modificación y distribución bajo esta misma licencia.
 * Prohibido: uso comercial o redistribución con fines de lucro sin autorización expresa.
 *
 * Para detalles completos, consulta el archivo LICENSE-MULTILOBBY.txt o contacta a:
 * 📧 Flores Nicolas Eugenio.com
 */
package com.tuplugin.commands;

import com.tuplugin.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyConfigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("multilobby.config")) {
            sender.sendMessage("§cNo tienes permiso para usar este comando.");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cEste comando solo puede ser ejecutado por jugadores.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§eUso: /lobbyconfig setitem <material> <nombre_con_guiones> <slot>");
            return true;
        }

        if (args[0].equalsIgnoreCase("setitem")) {
            if (args.length < 4) {
                sender.sendMessage("§eUso: /lobbyconfig setitem <material> <nombre_con_guiones> <slot>");
                return true;
            }

            Material material = Material.matchMaterial(args[1]);
            if (material == null) {
                sender.sendMessage("§cMaterial inválido.");
                return true;
            }

            String name = args[2].replace("_", " "); // Soporta nombres con espacios usando _
            int slot;
            try {
                slot = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                sender.sendMessage("§cSlot inválido.");
                return true;
            }

            // Guardar en config
            Main plugin = Main.getInstance();
            plugin.getConfig().set("menu-item.material", material.toString());
            plugin.getConfig().set("menu-item.name", "&b" + name);
            plugin.getConfig().set("menu-item.slot", slot);
            plugin.saveConfig();
            plugin.reloadConfig();

            sender.sendMessage("§aÍtem del menú actualizado:");
            sender.sendMessage("§7Material: §f" + material);
            sender.sendMessage("§7Nombre: §f" + name);
            sender.sendMessage("§7Slot: §f" + slot);
            return true;
        }

        sender.sendMessage("§cSubcomando desconocido.");
        return true;
    }
}
