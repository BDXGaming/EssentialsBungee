package me.bdx.essentialsbungee.config;

import me.bdx.essentialsbungee.Essentialsbungee;
import me.bdx.essentialsbungee.Utils.LoggerControl;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigLoader {

    public static Configuration configuration;

    public static void setupBungee(){
        if (!Essentialsbungee.essentialsbungee.getDataFolder().exists())
            Essentialsbungee.essentialsbungee.getDataFolder().mkdir();

        File file = new File(Essentialsbungee.essentialsbungee.getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = Essentialsbungee.essentialsbungee.getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                LoggerControl.logWarning(e.toString());
            }
        }

        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(Essentialsbungee.essentialsbungee.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            LoggerControl.logWarning(e.toString());
        }
    }

    public static void reload(){
        setupBungee();
    }

    public static Configuration get(){
        return configuration;
    }

}
