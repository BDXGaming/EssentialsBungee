package me.bdx.essentialsbungee.commands;

import me.bdx.essentialsbungee.Essentialsbungee;
import me.bdx.essentialsbungee.Utils.EssentialsBungeeConstants;
import me.bdx.essentialsbungee.managers.WhitelistManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class WhitelistCommand extends Command {

    public WhitelistCommand(){
        super("gwhitelist", "", "globalwhitelist");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission(EssentialsBungeeConstants.WHITELIST_COMMNAND_PERMISSION)){
            if(args.length >0){

                //Lists the users Who are whitelisted on the server
                if(args[0].equals("list")){
                    //Checks if user has list subcommand permission
                    sender.sendMessage(new TextComponent("Whitelisted Users: \n"+WhitelistManager.whitelistedUsersMap.keySet().toString()));
                }

                //Adds the given user to the whitelist
                else if(args[0].equals("add")){
                    //Checks if a user has been provided
                    if(args.length >1){
                        String user = args[1];
                        Essentialsbungee.whitelistManager.addWhitelistedUser(user);
                        sender.sendMessage(new TextComponent(ChatColor.GREEN + "Added "+ user + " to the whitelist!"));

                    }else{
                        sender.sendMessage(new TextComponent(ChatColor.YELLOW + "Please specify a user /gwhitelist add <user>"));
                    }
                }
                //Removes the given user to the whitelist
                else if(args[0].equals("remove")){
                    //Checks if a user has been provided
                    if(args.length >1){
                        String user = args[1];
                        Essentialsbungee.whitelistManager.removeWhitelistedUser(user);
                        sender.sendMessage(new TextComponent(ChatColor.GREEN + "Removed "+ user + " from the whitelist!"));

                    }else{
                        sender.sendMessage(new TextComponent(ChatColor.YELLOW + "Please specify a user /gwhitelist remove <user>"));
                    }
                }
                //Disables the whitelist (Turns the whitelist off)
                else if(args[0].equals("off")){
                    if(Essentialsbungee.essentialsbungee.configcontroller.USE_WHITELIST){
                        Essentialsbungee.essentialsbungee.configcontroller.USE_WHITELIST = false;
                        sender.sendMessage(new TextComponent(ChatColor.RED + "Proxy whitelist has been disabled!"));
                    }else{
                        sender.sendMessage(new TextComponent("The whitelist is already disabled!"));
                    }
                }
                //Enables the whitelist (Turns on the whitelist)
                else if(args[0].equals("on")){
                    if(!(Essentialsbungee.essentialsbungee.configcontroller.USE_WHITELIST)){
                        Essentialsbungee.essentialsbungee.configcontroller.USE_WHITELIST = true;
                        sender.sendMessage(new TextComponent(ChatColor.GREEN + "Proxy whitelist has been enabled!"));
                    }else{
                        sender.sendMessage(new TextComponent("The whitelist is already enabled!"));
                    }
                }
                //Checks the current status of the global whitelist
                else if(args[0].equals("status")){
                    if(Essentialsbungee.essentialsbungee.configcontroller.USE_WHITELIST){
                        sender.sendMessage(new TextComponent("The whitelist is " + ChatColor.GREEN + "enabled!"));
                    }else{
                        sender.sendMessage(new TextComponent("The whitelist is " + ChatColor.RED + "disabled!"));
                    }
                }
            }
        }else{
            sender.sendMessage(new TextComponent(EssentialsBungeeConstants.MISSING_PERMISSION_RESPONSE));
        }
    }
}
