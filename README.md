# MultiLobby

![MultiLobby](https://img.shields.io/badge/version-1.0-brightgreen) ![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20+-blue)

MultiLobby es un plugin avanzado para servidores Spigot/Paper 1.20+ que permite gestionar múltiples lobbies, menús interactivos y configuraciones personalizadas de manera sencilla y profesional. Ideal para redes de servidores o servidores con múltiples áreas de espera.

---

## Características

- Gestión de múltiples lobbies con nombres personalizados.
- Menú gráfico interactivo para selección rápida de lobbies.
- Configuración dinámica del ítem del menú mediante comandos.
- Recarga de configuración en tiempo real con `/multilobbyreload`.
- Compatible con versiones Spigot y Paper 1.20+.
- Sistema de permisos para controlar acceso a comandos.

---

## Comandos

| Comando | Descripción | Permiso |
|---------|-------------|---------|
| `/setlobby <nombre>` | Establece una ubicación de lobby con nombre personalizado. | `lobby.set` |
| `/lobby <nombre>` | Teletransporta al jugador al lobby indicado. | `lobby.use` |
| `/lobbymenu` | Abre el menú gráfico con los lobbies disponibles. | `lobby.menu` |
| `/lobbyconfig setitem <material> <nombre_con_guiones> <slot>` | Configura el ítem del menú de selección. | `multilobby.config` |
| `/multilobbyreload` | Recarga la configuración del plugin sin reiniciar. | `multilobby.reload` |

---

## Permisos

- `lobby.set`: Permite definir nuevos lobbies.
- `lobby.use`: Permite teletransportarse a lobbies.
- `lobby.menu`: Permite abrir el menú gráfico.
- `multilobby.config`: Permite configurar el ítem del menú.
- `multilobby.reload`: Permite recargar la configuración.

---

## Configuración

La configuración se encuentra en `config.yml`. Puedes editarla manualmente o usar los comandos para modificarla en tiempo real. Recuerda usar `/multilobbyreload` para aplicar cambios sin reiniciar el servidor.

---

## Licencia

Este plugin está licenciado bajo la **Licencia Extendida de Código Abierto con Restricción Comercial**. 

- Puedes usar, modificar y compartir este plugin siempre que mantengas la misma licencia y hagas públicas tus modificaciones.
- No está permitido vender o lucrar con este plugin ni con sus versiones modificadas sin autorización expresa.
- El autor mantiene el derecho exclusivo para la comercialización y distribución con fines comerciales.

---

## Contacto

Para licencias comerciales, soporte o consultas, contacta al autor:

📧 floresnicolaseugenio874@gmail.com
🌐 (https://twitch.tv/mundobarista.com)

---

*MultiLobby - (c) 2025 TuNombre*  
*Licencia de código abierto con uso no comercial.*

---

## Instalación rápida

1. Coloca el archivo `.jar` en la carpeta `plugins` de tu servidor Spigot/Paper 1.20+.
2. Arranca o recarga el servidor.
3. Configura los lobbies con `/setlobby <nombre>`.
4. Usa `/lobbymenu` para abrir el menú y elegir un lobby.
5. Personaliza el menú con `/lobbyconfig setitem <material> <nombre_con_guiones> <slot>`.
6. Aplica cambios con `/multilobbyreload`.

---

## ¡Disfruta de MultiLobby y mejora la experiencia de tus jugadores!

