package me.bdx.essentialsbungee;

import me.bdx.essentialsbungee.Utils.LoggerControl;
import me.bdx.essentialsbungee.commands.ReloadCommand;
import me.bdx.essentialsbungee.commands.UserInfoCommand;
import me.bdx.essentialsbungee.commands.WhitelistCommand;
import me.bdx.essentialsbungee.config.ConfigController;
import me.bdx.essentialsbungee.config.ConfigLoader;
import me.bdx.essentialsbungee.handlers.DisconnectEvent;
import me.bdx.essentialsbungee.handlers.JoinEvent;
import me.bdx.essentialsbungee.managers.WhitelistManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

public final class Essentialsbungee extends Plugin {

    public static Essentialsbungee essentialsbungee;
    public ConfigController configcontroller;
    public static WhitelistManager whitelistManager;

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
        whitelistManager = new WhitelistManager();

        //Registers the listeners
        getProxy().getPluginManager().registerListener(this, new DisconnectEvent());
        getProxy().getPluginManager().registerListener(this, new JoinEvent());

        //Register Commands
        getProxy().getPluginManager().registerCommand(this, new WhitelistCommand());
        getProxy().getPluginManager().registerCommand(this, new ReloadCommand());
        getProxy().getPluginManager().registerCommand(this, new UserInfoCommand());

        //Log in console that plugin is online
        getProxy().getConsole().sendMessage(new TextComponent("[EssentialsBungee]: "+ChatColor.GREEN + "Plugin version 0.1.0 has loaded!"));
    }

    @Override
    public void onDisable() {

        //Saves Whitelist
        whitelistManager.saveWhitelist();

        getProxy().getConsole().sendMessage(new TextComponent("[EssentialsBungee]: "+ChatColor.RED + "Plugin version 0.1.0 has been disabled!"));
    }
}
