package me.bdx.essentialsbungee.config;

import me.bdx.essentialsbungee.EssentialsBungee;
import me.bdx.essentialsbungee.Utils.LoggerControl;
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
        if (!EssentialsBungee.essentialsbungee.getDataFolder().exists())
            EssentialsBungee.essentialsbungee.getDataFolder().mkdir();

        File file = new File(EssentialsBungee.essentialsbungee.getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = EssentialsBungee.essentialsbungee.getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                LoggerControl.logWarning(e.toString());
            }
        }

        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(EssentialsBungee.essentialsbungee.getDataFolder(), "config.yml"));
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
