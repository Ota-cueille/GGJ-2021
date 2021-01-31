package fr.esaudm.ggj2021.shell;

import java.io.File;

public class ChangeDirectoryCommand extends Command {

    private String directory;

    public ChangeDirectoryCommand(String cmd) {
        if(cmd.contains(" ")) {
            this.directory = cmd.split(" +", 2)[1];
        }
    }

    @Override
    public void handle() {
        File currDir = new File(Shell.currentDirectory);
        String[] splitted = ("/" + this.directory + "/").split("\\.\\.");
        for (int i = 0; i < splitted.length; i++) {
            if (i > 0) {
                //if (currDir.equals(new File(new File("Game/").getAbsolutePath()))) {
                if(currDir.getAbsolutePath().equals(new File("Game/").getAbsolutePath())) {
                    System.out.println("you can not quit game root directory !");
                    return;
                }
                currDir = currDir.getParentFile();
            }
            currDir = new File(currDir.getPath() + "/" + splitted[i]);
        }
        if(currDir.isDirectory()) {
            Shell.currentDirectory = currDir.getAbsolutePath();
        } else {
            System.out.println("not a directory !");
        }
    }
}
