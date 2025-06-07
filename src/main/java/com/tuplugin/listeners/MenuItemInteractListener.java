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

import com.tuplugin.utils.MenuUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class MenuItemInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!(event.getHand() == EquipmentSlot.HAND)) return; // solo main hand

        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null) return;

        String configuredMaterial = MenuUtils.getConfig().getString("menu-item.material", "COMPASS");
        if (item.getType().toString().equalsIgnoreCase(configuredMaterial)) {
            event.setCancelled(true);
            player.performCommand("lobbymenu");
        }
    }
}
