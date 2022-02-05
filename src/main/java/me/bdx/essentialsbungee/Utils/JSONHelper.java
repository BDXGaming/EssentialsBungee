package me.bdx.essentialsbungee.Utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONHelper {

    /**
     * Loads the given JSON file as a FileReader. If not exists creates an empty JSON file at the specified file
     * location.
     * @param filePath The String path of the file location
     * @return the FileReader of the JSON file or null if unable to find and/or create the file
     */
    public static FileReader loadJSONFile(String filePath) {

        File f = new File(filePath);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException exception) {
                LoggerControl.logWarning(exception.toString());
            }

            try (FileReader reader = new FileReader(filePath)) {
                return reader;

            } catch (IOException e) {
                LoggerControl.logWarning(e.toString());
            }

        }
        return null;
    }
}
