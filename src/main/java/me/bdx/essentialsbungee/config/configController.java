package me.bdx.essentialsbungee.config;

import java.util.ArrayList;
import java.util.List;

public class configController {

    public boolean RECONNECT_SERVER_STATUS;
    public String RECONNECT_SERVER_NAME;
    public boolean USE_WHITELIST;
    public List<?> WHITELISTED_USERS;
    public String REFUSED_CONNECTION;

    public configController(){
        this.RECONNECT_SERVER_STATUS = configLoader.get().getBoolean("use-reconnect-server");
        this.RECONNECT_SERVER_NAME = configLoader.get().getString("reconnect-server");
        this.USE_WHITELIST = configLoader.get().getBoolean("use-whitelist");
        this.WHITELISTED_USERS =  configLoader.get().getList("whitelisted-users");
        this.REFUSED_CONNECTION = configLoader.get().getString("no-connect-message");
    }

    public void reload(){
        this.RECONNECT_SERVER_STATUS = configLoader.get().getBoolean("use-reconnect-server");
        this.RECONNECT_SERVER_NAME = configLoader.get().getString("reconnect-server");
        this.USE_WHITELIST = configLoader.get().getBoolean("use-whitelist");
        this.WHITELISTED_USERS =  configLoader.get().getList("whitelisted-users");
        this.REFUSED_CONNECTION = configLoader.get().getString("no-connect-message");
    }


}
