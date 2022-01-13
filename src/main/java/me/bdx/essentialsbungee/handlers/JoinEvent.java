package me.bdx.essentialsbungee.handlers;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class JoinEvent implements Listener {
    private final ArrayList<String> AllowedUsers = new ArrayList<>(Collections.singletonList("BDX____"));


    @EventHandler
    public void OnJoinEvent(PreLoginEvent event){
        if(!(AllowedUsers.contains(event.getConnection().getName()))){
            event.setCancelReason(new TextComponent(ChatColor.RED + "You are not permitted to connect!"));
            event.setCancelled(true);
        }
    }

}
