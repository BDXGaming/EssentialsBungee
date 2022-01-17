package me.bdx.essentialsbungee.Utils;

import net.md_5.bungee.api.ChatColor;

public class EssentialsBungeeConstants {

    /** The permission needed to use the whitelist command*/
    public static final String WHITELIST_COMMAND_PERMISSION = "essentialsBungee.whitelist";

    /** The permission needed to use the reload command */
    public static final String RELOAD_COMMAND_PERMISSION = "essentialsBungee.reload";

    /** The permission needed for the userinfo command */
    public static final String USERINFO_COMMAND_PERMISSION = "essentialsBungee.userinfo";

    /** The permission needed for the userinfo command ip*/
    public static final String USERINFO_IP_COMMAND_PERMISSION = "essentialsBungee.userinfo.ip";

    /** The permission needed to check the status of a specified server */
    public static final String SERVER_STATUS_COMMAND_PERMISSION  = "essentialsBungee.serverstatus";

    /** The response given for any missing permissions */
    public static final String MISSING_PERMISSION_RESPONSE = ChatColor.RED + "You do not have the required permissions to use this command!";
}
