package me.bdx.essentialsbungee.handlers;

import me.bdx.essentialsbungee.EssentialsBungee;
import me.bdx.essentialsbungee.events.WhitelistLoginFailEvent;
import me.bdx.essentialsbungee.events.WhitelistedPlayerLoginEvent;
import me.bdx.essentialsbungee.managers.WhitelistManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import java.util.List;
import java.util.UUID;

public class JoinEvent implements Listener {
    private final List<UUID> AllowedUsers = WhitelistManager.getInstance().getWhitelistedUsers();

    @EventHandler
    public void onJoinEvent(LoginEvent event){

        // Checks if the whitelist is enabled
        if(EssentialsBungee.essentialsbungee.configcontroller.USE_WHITELIST){

            // Checks if the connection via UUID is NOT whitelisted
            if(!(AllowedUsers.contains(event.getConnection().getUniqueId()))){

                // Calls the event for users joining who are not whitelisted
                WhitelistLoginFailEvent customEvent = new WhitelistLoginFailEvent(event);
                ProxyServer.getInstance().getPluginManager().callEvent(customEvent);

                // Handles the event if it has been cancelled
                if(customEvent.getCancelled()){
                    return;
                }

                // Logs that a non-whitelisted user joined the server
                ProxyServer.getInstance().getConsole().sendMessage(new TextComponent("[EssentialsBungee]: Player with UUID "+
                        event.getConnection().getUniqueId() + " Tried to connect!"));
                ProxyServer.getInstance().getLogger().info("Player with UUID "+ event.getConnection().getUniqueId()
                        + " Tried to connect!");

                // Cancels the login event for the non-whitelisted player with the custom message
                event.setCancelReason(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                        EssentialsBungee.essentialsbungee.configcontroller.REFUSED_CONNECTION)));
                event.setCancelled(true);
            }

            // Calls the event for a whitelisted user joining
            WhitelistedPlayerLoginEvent customEvent = new WhitelistedPlayerLoginEvent(event);
            ProxyServer.getInstance().getPluginManager().callEvent(customEvent);

            // Checks if the custom event is cancelled which would prevent the player from joining
            if(customEvent.getCancelled()){
                event.setCancelled(true);
            }

        }
    }

}
