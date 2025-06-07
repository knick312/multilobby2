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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuItemListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        // Solo mano principal
        if (e.getHand() != EquipmentSlot.HAND) return;

        // Solo clic derecho aire o bloque
        Action action = e.getAction();
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) return;

        // Cargar configuración
        String expectedName = Main.getInstance().getConfig().getString("menu-item.name");
        String expectedMaterial = Main.getInstance().getConfig().getString("menu-item.material");

        if (expectedName == null || expectedMaterial == null) return;

        Material materialFromConfig = Material.matchMaterial(expectedMaterial);
        if (materialFromConfig == null) return;

        // Comparar material y nombre (colores traducidos con §)
        if (item.getType() == materialFromConfig &&
                meta.getDisplayName().equalsIgnoreCase(expectedName.replace("&", "§"))) {

            e.setCancelled(true);
            player.openInventory(MenuUtils.createLobbyMenu(player));
        }
    }
}
