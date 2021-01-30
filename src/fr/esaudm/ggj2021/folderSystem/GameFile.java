package fr.esaudm.ggj2021.folderSystem;

import java.io.File;
import java.util.Scanner;

public class GameFile {
    private String buffer;
    private Boolean infested;

    public GameFile(String path) {
        try {
            File gamefile = new File(path);
            Scanner reader = new Scanner(gamefile);
            while (reader.hasNext()) {
                this.buffer += reader.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
