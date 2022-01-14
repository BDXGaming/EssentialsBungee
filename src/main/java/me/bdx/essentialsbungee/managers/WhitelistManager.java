package me.bdx.essentialsbungee.managers;

import me.bdx.essentialsbungee.Essentialsbungee;
import me.bdx.essentialsbungee.Utils.MojangPlayerHelper;
import net.md_5.bungee.api.ProxyServer;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WhitelistManager {

    public static ArrayList<UUID> WhitelistedUsers;

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

    public void LoadWhitelist() {

        if (WhitelistedUsers == null) {
            WhitelistedUsers = new ArrayList<>();
        }

        List<?> userNames = Essentialsbungee.essentialsbungee.configcontroller.WHITELISTED_USERS;

        for (Object user : userNames) {

            UUID uuid = MojangPlayerHelper.getUniqueId((String) user);
            if(!WhitelistedUsers.contains(uuid)){
                WhitelistedUsers.add(uuid);
            }

        }

    }

    public void saveWhitelist() {
        JSONArray whitelist = new JSONArray();
        whitelist.addAll(WhitelistedUsers);

        //Write JSON file
        try (FileWriter file = new FileWriter(Essentialsbungee.essentialsbungee.getDataFolder().toString()+"\\whitelist.json")) {

            file.write(whitelist.toJSONString());
            file.flush();

        } catch (IOException exception) {
            Essentialsbungee.essentialsbungee.getProxy().getLogger().warning(exception.toString());
        }
    }

    public void loadSavedWhitelist() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(Essentialsbungee.essentialsbungee.getDataFolder().toString()+"\\whitelist.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray whitelist = (JSONArray) obj;
            ArrayList<UUID> WhitelistTemp = new ArrayList<>();

            for(int i=0; i<whitelist.toArray().length; i++){
                String stringUUID = (String) whitelist.get(i);
                UUID uuid = UUID.fromString(stringUUID);
                WhitelistTemp.add(uuid);
            }
            WhitelistedUsers = WhitelistTemp;

        } catch (IOException | ParseException e) {

            if(e instanceof FileNotFoundException){
                File f = new File(Essentialsbungee.essentialsbungee.getDataFolder().toString()+ "\\whitelist.json");
                if(!f.exists()){
                    f.mkdirs();
                }
            }

            Essentialsbungee.essentialsbungee.getProxy().getLogger().warning(e.toString());
        }

    }
}
