package me.bdx.essentialsbungee.handlers;

import me.bdx.essentialsbungee.Essentialsbungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import java.util.List;

public class JoinEvent implements Listener {
    private final List<?> AllowedUsers = Essentialsbungee.essentialsbungee.configcontroller.WHITELISTED_USERS;


    @EventHandler
    public void OnJoinEvent(PreLoginEvent event){
        if(!(AllowedUsers.contains(event.getConnection().getName()))){
            event.setCancelReason(new TextComponent(ChatColor.RED + "You are not permitted to connect!"));
            event.setCancelled(true);
        }
    }

}
