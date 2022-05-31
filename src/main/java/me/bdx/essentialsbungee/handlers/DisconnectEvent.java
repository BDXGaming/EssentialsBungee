package me.bdx.essentialsbungee.handlers;

import me.bdx.essentialsbungee.EssentialsBungee;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class DisconnectEvent implements Listener {

    @EventHandler
    public void onDisconnectEvent(PlayerDisconnectEvent event) {

        //If set reconnect is enabled, sets the reconnection server to the server given in the config file
        if(EssentialsBungee.essentialsbungee.configcontroller.RECONNECT_SERVER_STATUS){
            ProxiedPlayer player = event.getPlayer();
            ServerInfo lobby = EssentialsBungee.essentialsbungee.getProxy().getServerInfo(EssentialsBungee.essentialsbungee.configcontroller.RECONNECT_SERVER_NAME);
            player.setReconnectServer(lobby);
        }
    }

}
