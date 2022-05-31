package me.bdx.essentialsbungee.events;

import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Event;

public class WhitelistLoginFailEvent extends Event {

    private final Connection connection;
    private final LoginEvent loginEvent;
    private boolean isCancelled;

    public WhitelistLoginFailEvent(LoginEvent event){
        this.connection = event.getConnection();
        this.loginEvent = event;
        this.isCancelled = false;
    }

    /**
     * Sets the status of the event as cancelled
     * If cancelled the connection will be allowed and the player can join.
     * @param isCancelled boolean whether or not the event is cancelled
     */
    public void setCancelled(boolean isCancelled){
        this.isCancelled = isCancelled;
    }

    /**
     * Gets the isCancelled bool
     * @return the isCancelled boolean
     */
    public boolean getCancelled(){
        return this.isCancelled;
    }

    /**
     * Gets the base LoginEvent
     * @return LoginEvent
     */
    public LoginEvent getLoginEvent() {
        return loginEvent;
    }

    /**
     * The connection from the LoginEvent
     * @return Connection
     */
    public Connection getConnection() {
        return connection;
    }
}
