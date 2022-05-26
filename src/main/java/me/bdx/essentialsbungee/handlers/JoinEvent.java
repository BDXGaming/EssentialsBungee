package me.bdx.essentialsbungee.handlers;

import me.bdx.essentialsbungee.Essentialsbungee;
import me.bdx.essentialsbungee.managers.WhitelistManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import java.util.List;
import java.util.UUID;

public class JoinEvent implements Listener {
    private final List<UUID> AllowedUsers = WhitelistManager.getInstance().getWhitelistedUsers();

    @EventHandler
    public void OnJoinEvent(LoginEvent event){

        if(Essentialsbungee.essentialsbungee.configcontroller.USE_WHITELIST){
            if(!(AllowedUsers.contains(event.getConnection().getUniqueId()))){
                event.setCancelReason(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                        Essentialsbungee.essentialsbungee.configcontroller.REFUSED_CONNECTION)));
                event.setCancelled(true);
            }
        }
    }

}
