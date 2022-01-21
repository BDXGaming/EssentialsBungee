package me.bdx.essentialsbungee.managers;

import me.bdx.essentialsbungee.Essentialsbungee;
import me.bdx.essentialsbungee.Utils.LoggerControl;
import me.bdx.essentialsbungee.Utils.MojangPlayerHelper;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class WhitelistManager {

    private static WhitelistManager whitelistManagerInstance;
    public ArrayList<UUID> whitelistedUsers;
    public HashMap<String, UUID> whitelistedUsersMap;

    private WhitelistManager() {
        whitelistedUsers = new ArrayList<>();
    }

    /**
     * Starts the whitelist manager
     */
    public void startWhitelist(){
        loadSavedWhitelist();
        loadWhitelist();
        whitelistManagerInstance = this;
    }

    /**
     * Starts the whitelist manager with the given whitelist
     * @param whitelist The ArrayList of UUIDs to use as a whitelist
     */
    public void startWhitelist(ArrayList<UUID> whitelist){
        whitelistedUsers = whitelist;
        loadSavedWhitelist();
        loadWhitelist();
        whitelistManagerInstance = this;
    }

    /**
     * Gets the instance of the whitelist manager
     * @return The instance of WhitelistManager
     */
    public static WhitelistManager getInstance(){

       if(whitelistManagerInstance == null){
           whitelistManagerInstance = new WhitelistManager();
       }
       return  whitelistManagerInstance;
    }

    /**
     * Loads the whitelisted users provided in the config file into the whitelist if not already present
     */
    public void loadWhitelist() {

        if (whitelistedUsers == null) {
            whitelistedUsers = new ArrayList<>();
        }

        if(whitelistedUsersMap == null){
            whitelistedUsersMap = new HashMap<>();
        }

        List<?> userNames = Essentialsbungee.essentialsbungee.configcontroller.WHITELISTED_USERS;

        for (Object user : userNames) {

            UUID uuid = MojangPlayerHelper.getUniqueId((String) user);
            if(!whitelistedUsers.contains(uuid)){
                whitelistedUsers.add(uuid);
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
            LoggerControl.logWarning(exception.toString());
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
            whitelistedUsers = WhitelistTemp;
            whitelistedUsersMap = whitelistMapTemp;

        } catch (IOException | ParseException e) {

            if(e instanceof FileNotFoundException){
                File f = new File(Essentialsbungee.essentialsbungee.getDataFolder(), "whitelist.json");
                if(!f.exists()){
                    try {
                        f.createNewFile();
                    } catch (IOException exception) {
                        LoggerControl.logWarning(exception.toString());
                    }
                    return;
                }
            }

            LoggerControl.logWarning(e.toString());
        }

    }

    /**
     * Adds a user to the whitelist during runtime
     * @param username String
     * @param uuid UUID
     */
    public void addWhitelistedUser(String username, UUID uuid){
        whitelistedUsersMap.put(username, uuid);
        whitelistedUsers.add(uuid);

        //Saves the updated whitelist when command is used to ensure whitelist accuracy
        saveWhitelist();
    }

    /**
     * Adds a user to the whitelist during runtime
     * @param username String
     */
    public boolean addWhitelistedUser(String username){

        UUID uuid = MojangPlayerHelper.getUniqueId(username);
        if(uuid == null){
            return false;
        }
        whitelistedUsersMap.put(username, uuid);
        whitelistedUsers.add(uuid);

        //Saves the updated whitelist when command is used to ensure whitelist accuracy
        saveWhitelist();

        return true;
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
        whitelistedUsers.remove(uuid);

        //Saves the updated whitelist when command is used to ensure whitelist accuracy
        saveWhitelist();
    }

    /**
     * Gets the whitelistedUsers
     * @return ArrayList<UUID> The whitelistedUsers
     */
    public ArrayList<UUID> getWhitelistedUsers(){
        return whitelistedUsers;
    }

    /**
     * Gets the map of whitelisted users and usernames
     * @return hashmap of whitelisted users
     */
    public HashMap<String ,UUID> getWhitelistedUsersMap(){
        return whitelistedUsersMap;
    }

}
