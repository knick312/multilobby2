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

import com.tuplugin.utils.LobbyStorage;
import com.tuplugin.utils.MenuUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class LobbyMenuClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView() == null) return;

        String title = event.getView().getTitle();
        if (!title.equals(MenuUtils.getMenuTitle())) return;

        event.setCancelled(true);
        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || !clicked.hasItemMeta()) return;

        Player player = (Player) event.getWhoClicked();

        // Buscar lobby por nombre en items del config
        for (String lobbyName : LobbyStorage.getAllLobbies()) {
            String itemName = MenuUtils.color(MenuUtils.getConfig().getString("items." + lobbyName + ".name", lobbyName));
            if (clicked.getItemMeta().getDisplayName().equals(itemName)) {
                MenuUtils.teleportToLobby(player, lobbyName);
                player.closeInventory();
                return;
            }
        }
    }
}
