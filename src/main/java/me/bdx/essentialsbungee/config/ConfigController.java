package me.bdx.essentialsbungee.config;

import net.md_5.bungee.config.Configuration;

import java.util.List;

public class ConfigController {

    public boolean RECONNECT_SERVER_STATUS;
    public String RECONNECT_SERVER_NAME;
    public boolean USE_WHITELIST;
    public List<?> WHITELISTED_USERS;
    public String REFUSED_CONNECTION;

    public ConfigController(){
        Configuration config = ConfigLoader.get();
        this.RECONNECT_SERVER_STATUS = config.getBoolean("use-reconnect-server");
        this.RECONNECT_SERVER_NAME = config.getString("reconnect-server");
        this.USE_WHITELIST = config.getBoolean("use-whitelist");
        this.WHITELISTED_USERS =  config.getList("whitelisted-users");
        this.REFUSED_CONNECTION = config.getString("no-connect-message");
    }

    public void reload(){
        Configuration config = ConfigLoader.get();
        this.RECONNECT_SERVER_STATUS = config.getBoolean("use-reconnect-server");
        this.RECONNECT_SERVER_NAME = config.getString("reconnect-server");
        this.USE_WHITELIST = config.getBoolean("use-whitelist");
        this.WHITELISTED_USERS =  config.getList("whitelisted-users");
        this.REFUSED_CONNECTION = config.getString("no-connect-message");
    }


}
