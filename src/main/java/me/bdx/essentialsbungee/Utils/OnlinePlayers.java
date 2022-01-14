package me.bdx.essentialsbungee.Utils;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;

public class OnlinePlayers {

    /**
     * Gets an arraylist of all currently online players
     * @return ArrayList<ProxiedPlayer>
     */
    public static ArrayList<ProxiedPlayer> getOnlinePlayers(){
        return (ArrayList<ProxiedPlayer>) ProxyServer.getInstance().getPlayers();
    }

    /**
     * Gets a list of the online players
     * @return Object[]
     */
    public static Object[] getOnlinePlayersList(){
        return ProxyServer.getInstance().getPlayers().toArray();
    }

    /**
     * Gets the names of all online players
     * @return ArrayList<String>
     */
    public static ArrayList<String> getOnlinePlayerNames(){
        ArrayList<ProxiedPlayer> players = getOnlinePlayers();
        ArrayList<String> onlinePlayerNames = new ArrayList<>();
        for(ProxiedPlayer p: players){
            onlinePlayerNames.add(p.getName());
        }
        return onlinePlayerNames;
    }

}
