package me.bdx.essentialsbungee.config;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ConfigController {

    public boolean RECONNECT_SERVER_STATUS;
    public String RECONNECT_SERVER_NAME;
    public boolean USE_WHITELIST;
    public List<?> WHITELISTED_USERS;
    public String REFUSED_CONNECTION;
    public List<String> REFUSED_CONNECTION_LIST;

    /**
     * Sets what the REFUSED_CONNECTION is to support using lists in connection messages
     */
    private void setRefusedConnection(){

        if(this.REFUSED_CONNECTION.equals("")){
            if(this.REFUSED_CONNECTION_LIST == null){
                this.REFUSED_CONNECTION = "You are not allowed to join.";
                return;
            }

            this.REFUSED_CONNECTION = "";
            StringBuilder builder = new StringBuilder();
            ProxyServer.getInstance().getConsole().sendMessage(new TextComponent("Running"));
            for (String s : REFUSED_CONNECTION_LIST) {
                builder.append(s).append("\n ");
            }
            this.REFUSED_CONNECTION = builder.toString();
        }
    }

    public ConfigController(){
        Configuration config = ConfigLoader.get();
        this.RECONNECT_SERVER_STATUS = config.getBoolean("use-reconnect-server");
        this.RECONNECT_SERVER_NAME = config.getString("reconnect-server");
        this.USE_WHITELIST = config.getBoolean("use-whitelist");
        this.WHITELISTED_USERS =  config.getList("whitelisted-users");
        this.REFUSED_CONNECTION = config.getString("no-connect-message");
        this.REFUSED_CONNECTION_LIST = config.getStringList("no-connect-message");
        this.setRefusedConnection();
    }

    public void reload(){
        Configuration config = ConfigLoader.get();
        this.RECONNECT_SERVER_STATUS = config.getBoolean("use-reconnect-server");
        this.RECONNECT_SERVER_NAME = config.getString("reconnect-server");
        this.USE_WHITELIST = config.getBoolean("use-whitelist");
        this.WHITELISTED_USERS =  config.getList("whitelisted-users");
        this.REFUSED_CONNECTION = config.getString("no-connect-message");
        this.REFUSED_CONNECTION_LIST = config.getStringList("no-connect-message");
        this.setRefusedConnection();
    }


}
