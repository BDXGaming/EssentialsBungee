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

    /** The permission needed to report a user */
    public static final String REPORT_COMMAND_PERMISSION  = "essentialsBungee.report";

    /** The permission needed to receive user report messages */
    public static final String REPORT_COMMAND_ALERT_PERMISSION  = "essentialsBungee.report.alert";

    /** Allows a user to use the staffchat command and see staff chat */
    public static final String STAFF_CHAT_COMMAND_PERMISSION  = "essentialsBungee.staffchat";

    /** The format of the chat label for staff chat messages */
    public static final String STAFF_CHAT_LABEL = ChatColor.RED + "[" + ChatColor.AQUA + "StaffChat"+ChatColor.RED +"]";

    /** The response given for any missing permissions */
    public static final String MISSING_PERMISSION_RESPONSE = ChatColor.RED + "You do not have the required permissions to use this command!";
}
