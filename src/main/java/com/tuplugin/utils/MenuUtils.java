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
package com.tuplugin.utils;

import com.tuplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MenuUtils {

    private static final Main plugin = Main.getInstance();

    public static FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    public static String getDefaultLobby() {
        return getConfig().getString("default-lobby");
    }

    public static String getMenuTitle() {
        return color(getConfig().getString("menu-title"));
    }

    public static void giveMenuItem(Player player) {
        FileConfiguration config = getConfig();

        String materialName = config.getString("menu-item.material");
        if (materialName == null) {
            // No está configurado, no poner nada
            return;
        }

        Material mat = Material.getMaterial(materialName.toUpperCase());
        if (mat == null) {
            player.sendMessage(color("&cMaterial inválido en configuración: " + materialName));
            return; // No poner ítem si material inválido
        }

        int amount = config.getInt("menu-item.amount", 1);
        int slot = config.getInt("menu-item.slot", 0);
        String displayName = color(config.getString("menu-item.name", "&bLobbys"));
        List<String> lore = colorList(config.getStringList("menu-item.lore"));

        ItemStack item = new ItemStack(mat, amount);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(displayName);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }

        player.getInventory().setItem(slot, item);
    }

    public static void teleportToLobby(Player player, String lobbyName) {
        Location loc = LobbyStorage.getLobby(lobbyName);
        if (loc != null) {
            player.teleport(loc);
            // Enviar título y subtítulo configurados
            player.sendTitle(
                    color(getConfig().getString("teleport-title", "&aTeletransportado al lobby!")),
                    color(getConfig().getString("teleport-subtitle", "&7Disfruta tu estadía.")),
                    getConfig().getInt("title1-fadein", 10),
                    getConfig().getInt("title2-stay", 70),
                    getConfig().getInt("title3-fadeout", 20)
            );
            // Sonido al teletransportar
            String soundName = getConfig().getString("teleport-sound");
            if (soundName != null && !soundName.isEmpty()) {
                try {
                    player.playSound(
                            player.getLocation(),
                            org.bukkit.Sound.valueOf(soundName.toUpperCase()),
                            (float) getConfig().getDouble("teleport-sound-volume", 1.0),
                            (float) getConfig().getDouble("teleport-sound-pitch", 1.0)
                    );
                } catch (IllegalArgumentException e) {
                    player.sendMessage(color("&cEl sonido configurado no existe: " + soundName));
                }
            }
        } else {
            player.sendMessage(color("&cNo se pudo teletransportar al lobby &b" + lobbyName));
        }
    }

    public static Inventory createLobbyMenu(Player player) {
        FileConfiguration config = getConfig();

        int size = config.getInt("menu-item.size", 27);
        String title = getMenuTitle();

        Inventory inv = Bukkit.createInventory(null, size, title);

        for (String lobbyName : LobbyStorage.getAllLobbies()) {
            String materialName = config.getString("items." + lobbyName + ".material");
            if (materialName == null) {
                // No está configurado, no poner item para esta lobby
                continue;
            }

            Material mat = Material.getMaterial(materialName.toUpperCase());
            if (mat == null) {
                player.sendMessage(color("&cMaterial inválido en configuración para lobby " + lobbyName + ": " + materialName));
                continue; // no agregar ítem inválido
            }

            String name = color(config.getString("items." + lobbyName + ".name", lobbyName));
            List<String> lore = colorList(config.getStringList("items." + lobbyName + ".lore"));
            int slot = config.getInt("items." + lobbyName + ".slot", 0);

            ItemStack item = new ItemStack(mat);
            ItemMeta meta = item.getItemMeta();

            if (meta != null) {
                meta.setDisplayName(name);
                meta.setLore(lore);
                item.setItemMeta(meta);
            }

            inv.setItem(slot, item);
        }

        return inv;
    }

    // Colorear string con &
    public static String color(String msg) {
        if (msg == null) return "";
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static List<String> colorList(List<String> list) {
        List<String> colored = new ArrayList<>();
        for (String s : list) {
            colored.add(color(s));
        }
        return colored;
    }
}
