package me.bdx.essentialsbungee.Utils;

import java.util.ArrayList;

public class StringUtils {

    /**
     * Recreates the behavior of Bukkit's copyPartialMatches and adds all Strings in iterable which are contained in
     * the given key
     * @param key String which is checked against
     * @param iterable The ArrayList which is iterated over for matches
     * @param current The ArrayList which the matches should be added to
     * @return The ArrayList of matches added to the given current ArrayList
     */
    public static ArrayList<String> copyPartialMatches(String key, Iterable<String> iterable, ArrayList<String> current){

        for(String item: iterable){
            if(item.toLowerCase().startsWith(key.toLowerCase())){
                current.add(item);
            }
        }
        return current;
    }

    /**
     * Converts a String list to a string
     * @param args The String list
     * @return A string of all components of the string list
     */
    public static String listToString(String[] args){
        StringBuilder stringBuilder = new StringBuilder();
        for(String arg: args){
            stringBuilder.append(arg).append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * Convers the String List to an string starting at the given index
     * @param args The String list to be converted
     * @param startIndex The starting index
     * @return The string of the args list
     */
    public static String partialStringToList(String[] args, int startIndex){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = startIndex; i < args.length; i++){
            stringBuilder.append(args[i]).append(" ");
        }
        return stringBuilder.toString();
    }

}
