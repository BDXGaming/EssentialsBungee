package me.bdx.essentialsbungee.commands;

import me.bdx.essentialsbungee.Essentialsbungee;
import me.bdx.essentialsbungee.Utils.EssentialsBungeeConstants;
import me.bdx.essentialsbungee.Utils.OnlinePlayers;
import me.bdx.essentialsbungee.Utils.StringUtils;
import me.bdx.essentialsbungee.managers.WhitelistManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.Arrays;

public class WhitelistCommand extends Command implements TabExecutor {

    private final String[] whitelistSubcommands = {"list", "add", "remove", "on", "off", "status"};

    public WhitelistCommand(){
        super("gwhitelist", "", "globalwhitelist");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission(EssentialsBungeeConstants.WHITELIST_COMMAND_PERMISSION)){
            if(args.length >0){

                //Lists the users Who are whitelisted on the server
                switch (args[0]) {
                    case "list":
                        //Checks if user has list subcommand permission
                        sender.sendMessage(new TextComponent("Whitelisted Users: \n" + WhitelistManager.getInstance().getWhitelistedUsersMap().keySet().toString()));
                        break;

                    //Adds the given user to the whitelist
                    case "add":
                        //Checks if a user has been provided
                        if (args.length > 1) {
                            String user = args[1];
                            boolean result = WhitelistManager.getInstance().addWhitelistedUser(user);
                            if(result){
                                sender.sendMessage(new TextComponent(ChatColor.GREEN + "Added " + user + " to the whitelist!"));
                            }else{
                                sender.sendMessage(new TextComponent(ChatColor.RED + "Error:  " + user + " is not a valid username!"));
                            }

                        } else {
                            sender.sendMessage(new TextComponent(ChatColor.YELLOW + "Please specify a user /gwhitelist add <user>"));
                        }
                        break;
                    //Removes the given user to the whitelist
                    case "remove":
                        //Checks if a user has been provided
                        if (args.length > 1) {
                            String user = args[1];
                            WhitelistManager.getInstance().removeWhitelistedUser(user);
                            sender.sendMessage(new TextComponent(ChatColor.GREEN + "Removed " + user + " from the whitelist!"));

                        } else {
                            sender.sendMessage(new TextComponent(ChatColor.YELLOW + "Please specify a user /gwhitelist remove <user>"));
                        }
                        break;
                    //Disables the whitelist (Turns the whitelist off)
                    case "off":
                        if (Essentialsbungee.essentialsbungee.configcontroller.USE_WHITELIST) {
                            Essentialsbungee.essentialsbungee.configcontroller.USE_WHITELIST = false;
                            sender.sendMessage(new TextComponent(ChatColor.RED + "Proxy whitelist has been disabled!"));
                        } else {
                            sender.sendMessage(new TextComponent("The whitelist is already disabled!"));
                        }
                        break;
                    //Enables the whitelist (Turns on the whitelist)
                    case "on":
                        if (!(Essentialsbungee.essentialsbungee.configcontroller.USE_WHITELIST)) {
                            Essentialsbungee.essentialsbungee.configcontroller.USE_WHITELIST = true;
                            sender.sendMessage(new TextComponent(ChatColor.GREEN + "Proxy whitelist has been enabled!"));
                        } else {
                            sender.sendMessage(new TextComponent("The whitelist is already enabled!"));
                        }
                        break;
                    //Checks the current status of the global whitelist
                    case "status":
                        if (Essentialsbungee.essentialsbungee.configcontroller.USE_WHITELIST) {
                            sender.sendMessage(new TextComponent("The whitelist is " + ChatColor.GREEN + "enabled!"));
                        } else {
                            sender.sendMessage(new TextComponent("The whitelist is " + ChatColor.RED + "disabled!"));
                        }
                        break;
                }
            }
        }else{
            sender.sendMessage(new TextComponent(EssentialsBungeeConstants.MISSING_PERMISSION_RESPONSE));
        }
    }


    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {

        ArrayList<String> completions = new ArrayList<>();

        if(sender.hasPermission(EssentialsBungeeConstants.WHITELIST_COMMAND_PERMISSION)){
            if(args.length <2){
                return StringUtils.copyPartialMatches(args[0], Arrays.asList(whitelistSubcommands), completions);
            }else{
                if(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")){
                    if(args[0].equalsIgnoreCase("add")){
                        return StringUtils.copyPartialMatches(args[1], OnlinePlayers.getOnlinePlayerNames(), completions);
                    }

                    return StringUtils.copyPartialMatches(args[1], WhitelistManager.getInstance().getWhitelistedUsersMap().keySet(), completions);
                }

            }
        }
        return completions;
    }

}
