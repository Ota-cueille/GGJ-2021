package fr.esaudm.ggj2021.shell;

import fr.esaudm.ggj2021.binedit.BinaryEditorWindow;
import fr.esaudm.ggj2021.mail.MailWindow;
import fr.esaudm.ggj2021.utils.Utils;

public class BinEditCommand extends Command {

    @Override
    public void handle() {
        System.out.println("Starting Binary Editor program...");
        Utils.sleep(1000);
        System.out.println("Loading complex mathematics formulas to convert characters to binary numbers...");
        Utils.sleep(600);
        new BinaryEditorWindow();
        System.out.println("Program started successfully");
    }

}
