package me.bdx.essentialsbungee.handlers;

import me.bdx.essentialsbungee.Utils.OnlinePlayers;
import me.bdx.essentialsbungee.managers.WhitelistManager;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import java.util.Arrays;
import java.util.List;

public class TabEvent implements Listener {

    private final String[] whitelistSubcommands = {"list", "add", "remove", "on", "off", "status"};
    private final List<String> subcommands = Arrays.asList(whitelistSubcommands);

    @EventHandler
    public void OnTabComplete(TabCompleteEvent event){

        if(event.getCursor().startsWith("/gwhitelist") || event.getCursor().startsWith("/globalwhitelist")) {
            String[] args = event.getCursor().split(" ");
            if(args.length <=2){
                event.getSuggestions().addAll(Arrays.asList(whitelistSubcommands));
            }else{
                event.getSuggestions().addAll(WhitelistManager.whitelistedUsersMap.keySet());
            }
        }
    }

}
