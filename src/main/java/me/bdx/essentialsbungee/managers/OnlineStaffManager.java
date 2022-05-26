package me.bdx.essentialsbungee.managers;

import me.bdx.essentialsbungee.Essentialsbungee;
import me.bdx.essentialsbungee.Utils.EssentialsBungeeConstants;
import me.bdx.essentialsbungee.Utils.OnlinePlayers;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OnlineStaffManager {

    private static OnlineStaffManager onlineStaffManager;
    private final ArrayList<ProxiedPlayer> onlineStaff;
    private final HashMap<String, List<String>> onlineStaffServers;

    private OnlineStaffManager(){
        onlineStaff = new ArrayList<>();
        onlineStaffServers = new HashMap<>();
    }

    /**
     * Gets the instance of the OnlineStaffManger
     * @return the instance of OnlineStaffManager
     */
    public static OnlineStaffManager getInstance(){
        if(onlineStaffManager == null){
            onlineStaffManager = new OnlineStaffManager();
        }
        return onlineStaffManager;
    }

    /**
     * Updates the online staff list with all online staff
     */
    public void updateOnlineStaff() {
        onlineStaffServers.clear();
        for (ProxiedPlayer player : OnlinePlayers.getOnlinePlayers()) {
            if (player.hasPermission(EssentialsBungeeConstants.STAFF_LIST_PERMISSION)) {
                if (!onlineStaff.contains(player)) {
                    onlineStaff.add(player);
                }

                if (onlineStaffServers.containsKey(player.getServer().getInfo().getName())) {
                    onlineStaffServers.get(player.getServer().getInfo().getName()).add(player.getName());
                } else {
                    List<String> playerList = new ArrayList<>();
                    playerList.add(player.getName());
                    onlineStaffServers.put(player.getServer().getInfo().getName(), playerList);
                }
            }

        }
    }

    /**
     * Gets the String message of the staff list
     * @return the String message
     */
    public String getOnlineStaffMessage(){
        updateOnlineStaff();
        StringBuilder message = new StringBuilder();
        message.append(ChatColor.YELLOW).append(ChatColor.BOLD).append("  Online Staff:  \n \n");
        for (String serverName: onlineStaffServers.keySet()){
            message.append(ChatColor.YELLOW).append(ChatColor.BOLD).append("    ").append(serverName).append("  \n");
            for (String playerName: onlineStaffServers.get(serverName)){
                message.append(ChatColor.WHITE).append("     - ").append(ChatColor.AQUA).append(playerName).append(" ").
                        append(ChatColor.GRAY).append("(").append(serverName).append(")\n");
            }

        }
        return message.toString();
    }

    /**
     * Gets the list of the online staff
     * @return the ArrayList of those with the stafflist permissin
     */
    public ArrayList<ProxiedPlayer> getOnlineStaff(){
        return onlineStaff;
    }

}
