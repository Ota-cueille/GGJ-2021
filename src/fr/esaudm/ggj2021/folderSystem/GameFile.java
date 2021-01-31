package fr.esaudm.ggj2021.folderSystem;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameFile {

    private File binary;

    public GameFile(String path) {
        this.binary = new File(path);
    }

    public void move(String path) {
        String new_file_name = path + this.binary.getName();
        File meh = new File(new_file_name);
        try {
            Files.copy(this.binary.toPath(), meh.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
