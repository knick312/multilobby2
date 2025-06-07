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

import com.tuplugin.utils.LobbyStorage;
import com.tuplugin.utils.MenuUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobbyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Solo jugadores pueden usar este comando.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(MenuUtils.color("&cUso correcto: /setlobby <nombre>"));
            return true;
        }

        Player player = (Player) sender;
        String lobbyName = args[0].toLowerCase();
        Location loc = player.getLocation();

        LobbyStorage.saveLobby(lobbyName, loc);
        player.sendMessage(MenuUtils.color("&aLobby &b" + lobbyName + " &aguardado correctamente."));

        return true;
    }
}
