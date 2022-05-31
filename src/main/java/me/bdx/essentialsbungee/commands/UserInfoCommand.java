package me.bdx.essentialsbungee.commands;

import me.bdx.essentialsbungee.Utils.EssentialsBungeeConstants;
import me.bdx.essentialsbungee.Utils.OnlinePlayers;
import me.bdx.essentialsbungee.Utils.StringUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;

public class UserInfoCommand extends Command implements TabExecutor {

    public UserInfoCommand(){
        super("userinfo", "" ,"user", "userlookup");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(sender.hasPermission(EssentialsBungeeConstants.USERINFO_COMMAND_PERMISSION)){
            if(args.length >=1){
                ProxiedPlayer player = OnlinePlayers.getPlayer(args[0]);
                if(player != null){
                    String message = ChatColor.translateAlternateColorCodes('&', "&e&lPlayer &r&a"
                            + args[0] + "&r&e&l information \n \n");

                    message += ChatColor.RESET +""+ ChatColor.YELLOW + "Username: " +ChatColor.WHITE+ player.getName()
                            + "\n";
                    message += ChatColor.YELLOW + "UUID: " +ChatColor.WHITE+ player.getUniqueId().toString() + "\n";
                    message += ChatColor.YELLOW + "Current Server: " +ChatColor.WHITE+ player.getServer().getInfo().
                            getName() + "\n";

                    if(sender.hasPermission(EssentialsBungeeConstants.USERINFO_IP_COMMAND_PERMISSION)){
                        message += ChatColor.YELLOW + "IP Address: " +ChatColor.WHITE+ player.getSocketAddress().
                                toString() + "\n";
                    }

                    sender.sendMessage(new TextComponent(message+ "\n "));

                }else{
                    sender.sendMessage(new TextComponent(ChatColor.DARK_RED + "Player "+ ChatColor.RED + args[0] +
                            ChatColor.DARK_RED + " is not currently online!"));
                }

            }else{
                sender.sendMessage(new TextComponent(ChatColor.YELLOW + "Please include a user /<command> <user>"));
            }
        }else{
            sender.sendMessage(new TextComponent(EssentialsBungeeConstants.MISSING_PERMISSION_RESPONSE));
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        ArrayList<String> completions = new ArrayList<>();
        if(sender.hasPermission(EssentialsBungeeConstants.USERINFO_COMMAND_PERMISSION)){
            return StringUtils.copyPartialMatches(args[0], OnlinePlayers.getOnlinePlayerNames(), completions);
        }
        return completions;
    }
}
