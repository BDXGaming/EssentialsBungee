package me.bdx.essentialsbungee.Utils;

import org.shanerx.mojang.Mojang;
import org.shanerx.mojang.PlayerProfile;
import java.util.UUID;
import java.util.regex.Pattern;

public class MojangPlayerHelper {

    private static final Mojang m = new Mojang();

    private static final Pattern UUID_FIX = Pattern.compile("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})");

    /**
     * Changes the UUID format and parses UUID from String
     * @param uuid String
     * @return uuid UUID
     */
    public static UUID formatUUID(String uuid) {
        uuid = uuid.replace("-", "");
        return UUID.fromString(UUID_FIX.matcher(uuid.replace("-", "")).replaceAll("$1-$2-$3-$4-$5"));
    }

    /**
     * Using the MojangAPI gets the UUID for the given username
     * @param username String
     * @return UUID
     */
    public static UUID getUniqueId(String username){

        try{
            String uuid = m.getUUIDOfUsername(username);
            return formatUUID(uuid);
        }catch (NullPointerException e){
            return null;
        }

    }

    /**
     * Converts the given UUID to a UtilPlayer instance
     * @param uuid UUID
     * @return UtilPlayer
     */
    public static UtilPlayer getPlayer(UUID uuid){
        PlayerProfile p = m.getPlayerProfile(String.valueOf(uuid));
        return new UtilPlayer(uuid, p.getUsername());
    }

}
