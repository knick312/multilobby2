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
package com.tuplugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

import com.tuplugin.utils.MenuUtils;

public class OpenLobbyMenuEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private boolean cancelled = false;
    private Inventory menu;

    public OpenLobbyMenuEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void call() {
        // Para llamar este evento se usa Bukkit.getPluginManager().callEvent(this)
        org.bukkit.Bukkit.getPluginManager().callEvent(this);
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public void openMenu() {
        if (menu == null) {
            menu = MenuUtils.createLobbyMenu(player);
        }
        player.openInventory(menu);
    }

    public static boolean isMenuItem(org.bukkit.inventory.ItemStack item, Player player) {
        if (item == null || !item.hasItemMeta()) return false;
        String menuItemName = MenuUtils.color(MenuUtils.getConfig().getString("menu-item.name"));
        String itemName = item.getItemMeta().getDisplayName();
        return itemName != null && itemName.equals(menuItemName);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
