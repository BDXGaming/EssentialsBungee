package me.bdx.essentialsbungee.commands;

import me.bdx.essentialsbungee.Utils.ChatUtils;
import me.bdx.essentialsbungee.Utils.EssentialsBungeeConstants;
import me.bdx.essentialsbungee.Utils.OnlinePlayers;
import me.bdx.essentialsbungee.Utils.StringUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;

public class ReportCommand extends Command implements TabExecutor {

    public ReportCommand(){
        super("greport", "", "report");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission(EssentialsBungeeConstants.REPORT_COMMAND_PERMISSION)){
            if(args.length >=1){
                String reportMessage = StringUtils.partialStringToList(args, 1);
                ChatUtils.broadcast(ChatColor.YELLOW +""+ChatColor.BOLD +"User Report \n \n"+ChatColor.YELLOW +
                        "Player: "+ChatColor.RED+sender.getName()+ChatColor.YELLOW +" reported "+ChatColor.RED+args[0]
                        +ChatColor.YELLOW + " for " +ChatColor.RED +reportMessage,
                        EssentialsBungeeConstants.REPORT_COMMAND_ALERT_PERMISSION);
            }else{
                sender.sendMessage(new TextComponent(ChatColor.YELLOW + "Please include a user to report!"));
            }
        }else{
            sender.sendMessage(new TextComponent(EssentialsBungeeConstants.MISSING_PERMISSION_RESPONSE));
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        ArrayList<String> completions = new ArrayList<>();
        if(sender.hasPermission(EssentialsBungeeConstants.REPORT_COMMAND_PERMISSION)){
            if(args.length <=1){
                StringUtils.copyPartialMatches(args[0], OnlinePlayers.getOnlinePlayerNames(), completions);
            }
        }
        return completions;
    }
}
