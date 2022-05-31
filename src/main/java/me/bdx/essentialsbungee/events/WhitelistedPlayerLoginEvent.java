package me.bdx.essentialsbungee.events;

import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Event;


public class WhitelistedPlayerLoginEvent extends Event {

    private final Connection connection;
    private final LoginEvent loginEvent;
    private boolean isCancelled;

    public WhitelistedPlayerLoginEvent(LoginEvent event){
        this.connection = event.getConnection();
        this.loginEvent = event;
        this.isCancelled = false;
    }

    public void setCancelled(boolean isCancelled){
        this.isCancelled = isCancelled;
    }

    public boolean getCancelled(){
        return this.isCancelled;
    }

    public LoginEvent getLoginEvent() {
        return loginEvent;
    }

    public Connection getConnection() {
        return connection;
    }
}
