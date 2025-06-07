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

package com.tuplugin;

import com.tuplugin.commands.LobbyCommand;
import com.tuplugin.commands.LobbyConfigCommand;
import com.tuplugin.commands.LobbyMenuCommand;
import com.tuplugin.commands.SetLobbyCommand;
import com.tuplugin.listeners.LobbyMenuClickListener;
import com.tuplugin.listeners.MenuItemInteractListener;
import com.tuplugin.listeners.OpenLobbyMenuEventListener;
import com.tuplugin.listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        // Registrar comandos
        getCommand("setlobby").setExecutor(new SetLobbyCommand());
        getCommand("lobby").setExecutor(new LobbyCommand());
        getCommand("lobbymenu").setExecutor(new LobbyMenuCommand());
        this.getCommand("lobbyconfig").setExecutor(new LobbyConfigCommand());


        // Registrar listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new MenuItemInteractListener(), this);
        getServer().getPluginManager().registerEvents(new LobbyMenuClickListener(), this);
        getServer().getPluginManager().registerEvents(new OpenLobbyMenuEventListener(), this);
    }

    public static Main getInstance() {
        return instance;
    }
}
