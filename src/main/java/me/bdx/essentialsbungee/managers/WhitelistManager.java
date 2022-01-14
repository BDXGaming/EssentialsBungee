package me.bdx.essentialsbungee.managers;

import me.bdx.essentialsbungee.Essentialsbungee;
import me.bdx.essentialsbungee.Utils.MojangPlayerHelper;
import net.md_5.bungee.api.chat.TextComponent;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class WhitelistManager {

    public static ArrayList<UUID> WhitelistedUsers;
    public static HashMap<String, UUID> whitelistedUsersMap;

    public WhitelistManager() {
        WhitelistedUsers = new ArrayList<>();
        loadSavedWhitelist();
        LoadWhitelist();
    }

    public WhitelistManager(ArrayList<UUID> whitelist) {
        WhitelistedUsers = whitelist;
        loadSavedWhitelist();
        LoadWhitelist();
    }

    /**
     * Loads the whitelisted users provided in the config file into the whitelist if not already present
     */
    public void LoadWhitelist() {

        if (WhitelistedUsers == null) {
            WhitelistedUsers = new ArrayList<>();
        }

        if(whitelistedUsersMap == null){
            whitelistedUsersMap = new HashMap<>();
        }

        List<?> userNames = Essentialsbungee.essentialsbungee.configcontroller.WHITELISTED_USERS;

        for (Object user : userNames) {

            UUID uuid = MojangPlayerHelper.getUniqueId((String) user);
            if(!WhitelistedUsers.contains(uuid)){
                WhitelistedUsers.add(uuid);
                whitelistedUsersMap.put((String) user, uuid);
            }

        }

    }

    /**
     * Saves the whitelist to the whitelist.json file
     */
    public void saveWhitelist() {

        JSONArray whitelist = new JSONArray();
        for(String key: whitelistedUsersMap.keySet()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", key);
            jsonObject.put("uuid", whitelistedUsersMap.get(key).toString());
            whitelist.add(jsonObject);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(Essentialsbungee.essentialsbungee.getDataFolder().toString()+"\\whitelist.json")) {

            file.write(whitelist.toJSONString());
            file.flush();

        } catch (IOException exception) {
            Essentialsbungee.essentialsbungee.getProxy().getLogger().warning(exception.toString());
        }
    }

    /**
     * Loads the whitelist from the whitelist.json file
     */
    public void loadSavedWhitelist() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(Essentialsbungee.essentialsbungee.getDataFolder().toString()+"\\whitelist.json")) {

            //Read JSON file
            Object obj = jsonParser.parse(reader);

            //Represents the whole JSONArray
            JSONArray whitelist = (JSONArray) obj;

            //PlaceHolder Arraylist for UUIDS and for Hashmap represenations
            ArrayList<UUID> WhitelistTemp = new ArrayList<>();
            HashMap<String, UUID> whitelistMapTemp = new HashMap<>();

            for(int i=0; i<whitelist.toArray().length; i++){
                JSONObject jsonObject = (JSONObject) whitelist.get(i);

                UUID uuid = UUID.fromString((String) jsonObject.get("uuid"));
                WhitelistTemp.add(uuid);
                whitelistMapTemp.put((String) jsonObject.get("name"), uuid);
            }
            WhitelistedUsers = WhitelistTemp;
            whitelistedUsersMap = whitelistMapTemp;

        } catch (IOException | ParseException e) {

            if(e instanceof FileNotFoundException){
                File f = new File(Essentialsbungee.essentialsbungee.getDataFolder(), "whitelist.json");
                if(!f.exists()){
                    try {
                        f.createNewFile();
                    } catch (IOException exception) {
                        Essentialsbungee.essentialsbungee.getProxy().getLogger().warning(exception.toString());
                    }
                    return;
                }
            }

            Essentialsbungee.essentialsbungee.getProxy().getLogger().warning(e.toString());
        }

    }

    /**
     * Adds a user to the whitelist during runtime
     * @param username String
     * @param uuid UUID
     */
    public void addWhitelistedUser(String username, UUID uuid){
        whitelistedUsersMap.put(username, uuid);
        WhitelistedUsers.add(uuid);

        //Saves the updated whitelist when command is used to ensure whitelist accuracy
        saveWhitelist();
    }

    /**
     * Adds a user to the whitelist during runtime
     * @param username String
     */
    public void addWhitelistedUser(String username){

        UUID uuid = MojangPlayerHelper.getUniqueId(username);
        whitelistedUsersMap.put(username, uuid);
        WhitelistedUsers.add(uuid);

        //Saves the updated whitelist when command is used to ensure whitelist accuracy
        saveWhitelist();
    }

    /**
     * Allows for users to be removed from the whitelist during runtime
     * @param username String
     */
    public void removeWhitelistedUser(String username){

        UUID uuid = MojangPlayerHelper.getUniqueId(username);
        if(whitelistedUsersMap.containsKey(username)){
            whitelistedUsersMap.remove(username, uuid);
        }
        WhitelistedUsers.remove(uuid);

        //Saves the updated whitelist when command is used to ensure whitelist accuracy
        saveWhitelist();
    }


}
