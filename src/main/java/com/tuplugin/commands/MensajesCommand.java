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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class MensajesCommand implements CommandExecutor {

    private final Main plugin;

    public MensajesCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando solo puede ser usado por jugadores.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("Uso: /mensajes <bienvenida|evento|anuncio>");
            return true;
        }

        String tipo = args[0].toLowerCase();

        List<String> mensajes = plugin.getConfig().getStringList("mensajes." + tipo);
        if (mensajes == null || mensajes.isEmpty()) {
            sender.sendMessage("No hay mensajes configurados para '" + tipo + "'");
            return true;
        }

        Player jugador = (Player) sender;
        String serverName = plugin.getServer().getName();

        for (String linea : mensajes) {
            linea = linea.replace("{player}", jugador.getName())
                    .replace("{server}", serverName);
            jugador.sendMessage(linea);
        }

        return true;
    }
}
