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

import com.tuplugin.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AnunciarCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(MessageUtils.color("&cUso correcto: /anunciar <mensaje>"));
            return true;
        }

        String message = String.join(" ", args);
        String formattedMessage = MessageUtils.color(message);

        // Enviar el mensaje a todos los jugadores
        sender.getServer().broadcastMessage(formattedMessage);
        return true;
    }
}
