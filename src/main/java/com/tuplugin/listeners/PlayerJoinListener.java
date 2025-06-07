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
package com.tuplugin.listeners;

import com.tuplugin.Main;
import com.tuplugin.utils.MenuUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String defaultLobby = MenuUtils.getDefaultLobby();

        if (defaultLobby != null && !defaultLobby.isEmpty()) {
            MenuUtils.teleportToLobby(player, defaultLobby);
        }

        // Dar el ítem menú
        MenuUtils.giveMenuItem(player);

        // Mensajes de bienvenida
        String joinMessage = MenuUtils.getConfig().getString("join-message");
        if (joinMessage != null && !joinMessage.isEmpty()) {
            event.setJoinMessage(MenuUtils.color(joinMessage.replace("{player}", player.getName())));
        }

        // Sonido de bienvenida
        String joinSoundName = MenuUtils.getConfig().getString("join-sound");
        if (joinSoundName != null && !joinSoundName.isEmpty()) {
            try {
                player.playSound(player.getLocation(), Sound.valueOf(joinSoundName.toUpperCase()), 1f, 1f);
            } catch (IllegalArgumentException e) {
                player.sendMessage(MenuUtils.color("&cSonido de bienvenida mal configurado: " + joinSoundName));
            }
        }
    }
}
