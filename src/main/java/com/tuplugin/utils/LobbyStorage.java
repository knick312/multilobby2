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
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.Set;

public class LobbyStorage {

    private static final Main plugin = Main.getInstance();

    public static void saveLobby(String name, Location loc) {
        FileConfiguration config = plugin.getConfig();
        config.set("lobbys." + name + ".world", loc.getWorld().getName());
        config.set("lobbys." + name + ".x", loc.getX());
        config.set("lobbys." + name + ".y", loc.getY());
        config.set("lobbys." + name + ".z", loc.getZ());
        config.set("lobbys." + name + ".yaw", loc.getYaw());
        config.set("lobbys." + name + ".pitch", loc.getPitch());
        plugin.saveConfig();
    }

    public static Location getLobby(String name) {
        FileConfiguration config = plugin.getConfig();
        if (!config.isSet("lobbys." + name)) return null;

        String worldName = config.getString("lobbys." + name + ".world");
        if (worldName == null) return null;

        if (Bukkit.getWorld(worldName) == null) return null;

        double x = config.getDouble("lobbys." + name + ".x");
        double y = config.getDouble("lobbys." + name + ".y");
        double z = config.getDouble("lobbys." + name + ".z");
        float yaw = (float) config.getDouble("lobbys." + name + ".yaw");
        float pitch = (float) config.getDouble("lobbys." + name + ".pitch");

        return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
    }

    public static Set<String> getAllLobbies() {
        FileConfiguration config = plugin.getConfig();
        if (!config.isConfigurationSection("lobbys")) return new HashSet<>();
        return config.getConfigurationSection("lobbys").getKeys(false);
    }
}
