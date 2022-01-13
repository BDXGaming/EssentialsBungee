package me.bdx.essentialsbungee.handlers;

import me.bdx.essentialsbungee.Essentialsbungee;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class DisconnectEvent implements Listener {

    @EventHandler
    public void onDisconnectEvent(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        ServerInfo lobby = Essentialsbungee.essentialsbungee.getProxy().getServerInfo("lobby");
        player.setReconnectServer(lobby);
    }

}
