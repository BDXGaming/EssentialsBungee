package me.bdx.essentialsbungee.commands;

import me.bdx.essentialsbungee.Utils.EssentialsBungeeConstants;
import me.bdx.essentialsbungee.managers.OnlineStaffManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class OnlineStaffCommand extends Command {

    public OnlineStaffCommand(){
        super("staff", "","gstaff", "stafflist");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(sender.hasPermission(EssentialsBungeeConstants.STAFF_LIST_COMMAND_PERMISSION)){
            sender.sendMessage(new TextComponent(String.valueOf(OnlineStaffManager.getInstance().getOnlineStaffMessage())));
        }else{
            sender.sendMessage(new TextComponent(EssentialsBungeeConstants.MISSING_PERMISSION_RESPONSE));
        }

    }
}
