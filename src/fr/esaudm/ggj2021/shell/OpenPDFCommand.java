package fr.esaudm.ggj2021.shell;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpenPDFCommand extends Command {

    private final String command;

    public OpenPDFCommand(String command) {
        this.command = command;
    }

    @Override
    public void handle() {
        if (!this.command.contains(" ")) {
            System.out.println("You have to specify a path to a PDF file");
            return;
        }
        String path = this.command.split(" +", 2)[1];
        File file = new File(Shell.currentDirectory + "/" + path);
        if (file.isFile() && file.getName().endsWith(".pdf")) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("This file does not exist or is not a PDF file");
        }
    }

}
