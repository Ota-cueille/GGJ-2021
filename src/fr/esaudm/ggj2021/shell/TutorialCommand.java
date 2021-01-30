package fr.esaudm.ggj2021.shell;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TutorialCommand extends Command {

    @Override
    public void handle() {
        try {
            Desktop.getDesktop().open(new File("Assets/documentation/Tutorial.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
