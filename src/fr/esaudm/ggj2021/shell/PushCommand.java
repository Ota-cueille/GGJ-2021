package fr.esaudm.ggj2021.shell;

import java.io.File;

public class PushCommand extends Command {

    private String file;

    public PushCommand(String cmd) {
        if(cmd.contains(" ")) {
            this.file = cmd.split(" +", 2)[1];
        }
    }

    @Override
    public void handle() {
        File test = new File(file);
        if(test.exists()) {
            System.out.println("check if the file have been patched ...");
            System.out.println("error no comprendo !!!");
        }
    }
}
