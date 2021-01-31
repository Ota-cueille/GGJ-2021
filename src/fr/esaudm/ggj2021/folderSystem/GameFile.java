package fr.esaudm.ggj2021.folderSystem;

import fr.esaudm.ggj2021.Main;
import fr.esaudm.ggj2021.reminder.ReminderWindow;

import java.io.File;
import java.nio.file.Files;

public class GameFile {

    private File binary;
    private Boolean infected;

    public GameFile(String path) {
        this.binary = new File(path);
        this.infected = false;
        if(this.binary.getName().equals("Infected.txt")) {
            this.infected = true;
        }
    }

    public void move(String path) {
        if(!infected) {
            String new_file_name = path + this.binary.getName();
            File meh = new File(new_file_name);
            try {
                Files.copy(this.binary.toPath(), meh.toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ReminderWindow rw = (ReminderWindow) Main.getWindowOfType(Main.WindowType.REMINDER);
            if(rw != null) {
                rw.addTrojanHorse();
            }
        }
    }
}
