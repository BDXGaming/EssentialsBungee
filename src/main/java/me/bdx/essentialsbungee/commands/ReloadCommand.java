package me.bdx.essentialsbungee.commands;

import me.bdx.essentialsbungee.EssentialsBungee;
import me.bdx.essentialsbungee.Utils.EssentialsBungeeConstants;
import me.bdx.essentialsbungee.config.ConfigLoader;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
public class ReloadCommand extends Command {

    public ReloadCommand(){
        super("ebreload", "" ,"essentialsbungeereload");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        //Checks if sender has permission to reload the config
        if(sender.hasPermission(EssentialsBungeeConstants.RELOAD_COMMAND_PERMISSION)){

            sender.sendMessage(new TextComponent(ChatColor.GRAY+"["+ChatColor.GREEN+"EssentialsBungee"+
                    ChatColor.GRAY + "] " +ChatColor.RESET+ChatColor.YELLOW+"Starting Reload"));
            //Reloads the config instance
            ConfigLoader.reload();
            //Reloads the stored config values
            EssentialsBungee.essentialsbungee.configcontroller.reload();
            sender.sendMessage(new TextComponent(ChatColor.GRAY+"["+ChatColor.GREEN+"EssentialsBungee"+
                    ChatColor.GRAY + "] " +ChatColor.RESET+ChatColor.GREEN+"Reload Complete"));

        }else{
            //Sends if the user lacks the permissions needed to use the reload command
            sender.sendMessage(new TextComponent(ChatColor.RED +
                    EssentialsBungeeConstants.MISSING_PERMISSION_RESPONSE));
        }
    }
}
