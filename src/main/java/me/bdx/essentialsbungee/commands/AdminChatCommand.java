package me.bdx.essentialsbungee.commands;

import me.bdx.essentialsbungee.Utils.ChatUtils;
import me.bdx.essentialsbungee.Utils.EssentialsBungeeConstants;
import me.bdx.essentialsbungee.Utils.StringUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class AdminChatCommand extends Command {

    public AdminChatCommand(){
        super("gadminchat", "",  "gac", "adminchat");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(sender.hasPermission(EssentialsBungeeConstants.ADMIN_CHAT_COMMAND_PERMISSION)){
            if(args.length >=1){
                String message = StringUtils.listToString(args);
                ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(EssentialsBungeeConstants.ADMIN_CHAT_LABEL + " " + ChatColor.WHITE+ sender.getName() +": "+ChatColor.DARK_RED + message));
                ChatUtils.broadcast(EssentialsBungeeConstants.ADMIN_CHAT_LABEL + " " +ChatColor.WHITE+ sender.getName() +": "+ChatColor.DARK_RED + message, EssentialsBungeeConstants.ADMIN_CHAT_COMMAND_PERMISSION);
            }else{
                sender.sendMessage(new TextComponent(ChatColor.YELLOW +"Please include a message to be sent!"));
            }
        }else{
            sender.sendMessage(new TextComponent(EssentialsBungeeConstants.MISSING_PERMISSION_RESPONSE));
        }
    }

}
