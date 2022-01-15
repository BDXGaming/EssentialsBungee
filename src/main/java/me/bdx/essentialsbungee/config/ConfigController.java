package me.bdx.essentialsbungee.config;

import java.util.List;

public class ConfigController {

    public boolean RECONNECT_SERVER_STATUS;
    public String RECONNECT_SERVER_NAME;
    public boolean USE_WHITELIST;
    public List<?> WHITELISTED_USERS;
    public String REFUSED_CONNECTION;

    public ConfigController(){
        this.RECONNECT_SERVER_STATUS = ConfigLoader.get().getBoolean("use-reconnect-server");
        this.RECONNECT_SERVER_NAME = ConfigLoader.get().getString("reconnect-server");
        this.USE_WHITELIST = ConfigLoader.get().getBoolean("use-whitelist");
        this.WHITELISTED_USERS =  ConfigLoader.get().getList("whitelisted-users");
        this.REFUSED_CONNECTION = ConfigLoader.get().getString("no-connect-message");
    }

    public void reload(){
        this.RECONNECT_SERVER_STATUS = ConfigLoader.get().getBoolean("use-reconnect-server");
        this.RECONNECT_SERVER_NAME = ConfigLoader.get().getString("reconnect-server");
        this.USE_WHITELIST = ConfigLoader.get().getBoolean("use-whitelist");
        this.WHITELISTED_USERS =  ConfigLoader.get().getList("whitelisted-users");
        this.REFUSED_CONNECTION = ConfigLoader.get().getString("no-connect-message");
    }


}
