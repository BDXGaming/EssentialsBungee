package me.bdx.essentialsbungee.Utils;

import me.bdx.essentialsbungee.Essentialsbungee;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.Collection;

public class OnlinePlayers {

    /**
     * Gets an arraylist of all currently online players
     * @return ArrayList<ProxiedPlayer>
     */
    public static Collection<ProxiedPlayer> getOnlinePlayers(){
        return Essentialsbungee.essentialsbungee.getProxy().getPlayers();
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
        Collection<ProxiedPlayer> players = getOnlinePlayers();
        ArrayList<String> onlinePlayerNames = new ArrayList<>();
        for(ProxiedPlayer p: players){
            onlinePlayerNames.add(p.getName());
        }
        return onlinePlayerNames;
    }

    /**
     * Checks if the given player is online
     * @param player String playername
     * @return boolean if the player is online
     */
    public static boolean checkIfPlayerOnline(String player){
        ArrayList<String> onlinePlayers = getOnlinePlayerNames();
        return onlinePlayers.contains(player);
    }

    /**
     * Gets the ProxiedPlayer instance for the player with the given name. If no player is online with the given name null is returned
     * @param name String
     * @return The ProxiedPlayer instance or null
     */
    public static ProxiedPlayer getPlayer(String name){
        if(checkIfPlayerOnline(name)){
            return ProxyServer.getInstance().getPlayer(name);
        }
        return null;
    }

}
