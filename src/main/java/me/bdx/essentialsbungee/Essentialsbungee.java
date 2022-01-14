package me.bdx.essentialsbungee;

import me.bdx.essentialsbungee.config.configController;
import me.bdx.essentialsbungee.config.configLoader;
import me.bdx.essentialsbungee.handlers.DisconnectEvent;
import me.bdx.essentialsbungee.handlers.JoinEvent;
import net.md_5.bungee.api.plugin.Plugin;

public final class Essentialsbungee extends Plugin {

    public static Essentialsbungee essentialsbungee;
    public configController configcontroller;

    @Override
    public void onEnable() {
        //Sets the instance of the Plugin
        essentialsbungee = this;

        //Loads the config file
        configLoader.setupBungee();

        //Loads the config values
        this.configcontroller = new configController();

        //Registers the disconnect listener
        getProxy().getPluginManager().registerListener(this, new DisconnectEvent());
        getProxy().getPluginManager().registerListener(this, new JoinEvent());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
