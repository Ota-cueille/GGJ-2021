package fr.esaudm.ggj2021.shell;

import java.io.File;

public class ListFilesCommand extends Command {

    @Override
    public void handle() {
        File[] files = new File(Shell.currentDirectory).listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println(f.getName() + "/");
            } else {
                System.out.println(f.getName());
            }
        }
    }

}
