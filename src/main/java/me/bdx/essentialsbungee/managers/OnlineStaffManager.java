package me.bdx.essentialsbungee.managers;

import me.bdx.essentialsbungee.Essentialsbungee;
import me.bdx.essentialsbungee.Utils.EssentialsBungeeConstants;
import me.bdx.essentialsbungee.Utils.OnlinePlayers;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class OnlineStaffManager {

    private static OnlineStaffManager onlineStaffManager;
    private final ArrayList<ProxiedPlayer> onlineStaff;
    private final HashMap<String, String> onlineStaffServers;

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

        for (ProxiedPlayer player : OnlinePlayers.getOnlinePlayers()) {
            if (player.hasPermission(EssentialsBungeeConstants.STAFF_LIST_PERMISSION)) {
                if(!onlineStaff.contains(player)){
                    onlineStaff.add(player);
                    onlineStaffServers.put(player.getName(), player.getServer().getInfo().getName());
                }
            }
        }

    }

    /**
     * Gets the String message of the staff list
     * @return the String message
     */
    public String getOnlineStaffMessage(){
        StringBuilder message = new StringBuilder();
        message.append(ChatColor.YELLOW).append(ChatColor.BOLD).append("  Online Staff:  \n \n");
        for (ProxiedPlayer player: OnlinePlayers.getOnlinePlayers()){
            if(player.hasPermission(EssentialsBungeeConstants.STAFF_LIST_PERMISSION)){
                message.append(ChatColor.WHITE).append(" - ").append(ChatColor.AQUA).append(player.getName()).append(" ").append(ChatColor.GRAY).append("(").append(player.getServer().getInfo().getName()).append(")\n");
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
