package me.bdx.essentialsbungee.commands;

import me.bdx.essentialsbungee.Utils.EssentialsBungeeConstants;
import me.bdx.essentialsbungee.Utils.TabCompleteHelper;
import net.md_5.bungee.api.*;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Set;

public class ServerStatusCommand extends Command implements TabExecutor {

    public ServerStatusCommand() {
        super("gstatus");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender.hasPermission(EssentialsBungeeConstants.SERVER_STATUS_COMMAND_PERMISSION)) {

            if (args.length >= 1) {
                String server = args[0];
                Set<String> servers = ProxyServer.getInstance().getServers().keySet();
                    if(!(args[0].equalsIgnoreCase("all"))){
                        if (servers.contains(server)) {
                            Callback<ServerPing> callback = (result, error) -> {
                                String baseMessage =ChatColor.GOLD + "Server " + ChatColor.AQUA + server + ChatColor.GOLD +" is currently ";
                                if (result != null) {
                                    sender.sendMessage(new TextComponent(baseMessage + ChatColor.GREEN + "online!"));
                                } else {
                                    sender.sendMessage(new TextComponent(baseMessage + ChatColor.RED + "offline!"));
                                }
                            };
                            ProxyServer.getInstance().getServerInfo(server).ping(callback);
                        }else {
                            sender.sendMessage(new TextComponent(ChatColor.YELLOW + "There is no server by the name " + ChatColor.RED + server));
                        }

                    }else{
                        sender.sendMessage(new TextComponent(ChatColor.YELLOW +""+ ChatColor.BOLD + "All server status \n \n" ));
                        for(String serverName: servers){
                            Callback<ServerPing> callback = (result, error) -> {
                                String baseMessage =ChatColor.GOLD + "Server " + ChatColor.AQUA + serverName + ChatColor.GOLD +" is currently ";
                                if (result != null) {
                                    sender.sendMessage(new TextComponent(baseMessage + ChatColor.GREEN + "online!"));
                                } else {
                                    sender.sendMessage(new TextComponent(baseMessage + ChatColor.RED + "offline!"));
                                }
                            };
                            ProxyServer.getInstance().getServerInfo(serverName).ping(callback);
                        }
                    }

            } else {
                sender.sendMessage(new TextComponent(ChatColor.YELLOW + "Please include a server /<command> <server>"));
            }

        } else {
            sender.sendMessage(new TextComponent(EssentialsBungeeConstants.MISSING_PERMISSION_RESPONSE));
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {

        ArrayList<String> completions = new ArrayList<>();
        if (sender.hasPermission(EssentialsBungeeConstants.SERVER_STATUS_COMMAND_PERMISSION)) {
            Set<String> servers = ProxyServer.getInstance().getServers().keySet();
            ArrayList<String> serverNames = new ArrayList<>(servers);
            serverNames.add("all");
            TabCompleteHelper.copyPartialMatches(args[0], serverNames, completions);
        }
        return completions;
    }
}
