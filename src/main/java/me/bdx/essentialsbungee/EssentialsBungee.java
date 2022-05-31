package me.bdx.essentialsbungee;

import me.bdx.essentialsbungee.Utils.LoggerControl;
import me.bdx.essentialsbungee.commands.*;
import me.bdx.essentialsbungee.config.ConfigController;
import me.bdx.essentialsbungee.config.ConfigLoader;
import me.bdx.essentialsbungee.handlers.DisconnectEvent;
import me.bdx.essentialsbungee.handlers.JoinEvent;
import me.bdx.essentialsbungee.managers.WhitelistManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

public final class EssentialsBungee extends Plugin {

    public static EssentialsBungee essentialsbungee;
    public ConfigController configcontroller;

    @Override
    public void onEnable() {
        //Sets the instance of the Plugin
        essentialsbungee = this;

        //Sets the logger which is to be used
        LoggerControl.setLogger(getLogger());

        //Loads the config file
        ConfigLoader.setupBungee();

        //Loads the config values
        this.configcontroller = new ConfigController();

        //Loads the Whitelist
        WhitelistManager.getInstance().startWhitelist();

        //Registers the listeners
        getProxy().getPluginManager().registerListener(this, new DisconnectEvent());
        getProxy().getPluginManager().registerListener(this, new JoinEvent());

        //Register Commands
        getProxy().getPluginManager().registerCommand(this, new WhitelistCommand());
        getProxy().getPluginManager().registerCommand(this, new ReloadCommand());
        getProxy().getPluginManager().registerCommand(this, new UserInfoCommand());
        getProxy().getPluginManager().registerCommand(this, new ServerStatusCommand());
        getProxy().getPluginManager().registerCommand(this, new ReportCommand());
        getProxy().getPluginManager().registerCommand(this, new StaffChatCommand());
        getProxy().getPluginManager().registerCommand(this, new OnlineStaffCommand());
        getProxy().getPluginManager().registerCommand(this, new AdminChatCommand());

        //Log in console that plugin is online
        getProxy().getConsole().sendMessage(new TextComponent("[EssentialsBungee]: "+ChatColor.GREEN +
                "Plugin version 0.2.5 has loaded!"));
    }

    @Override
    public void onDisable() {

        //Saves Whitelist
        WhitelistManager.getInstance().saveWhitelist();

        getProxy().getConsole().sendMessage(new TextComponent("[EssentialsBungee]: "+ChatColor.RED +
                "Plugin version 0.2.5 has been disabled!"));
    }
}
