package fr.esaudm.ggj2021.shell;

import fr.esaudm.ggj2021.Main;
import fr.esaudm.ggj2021.mail.MailWindow;
import fr.esaudm.ggj2021.utils.Utils;

public class MailCommand extends Command {

    @Override
    public void handle() {
        System.out.println("Starting Jean-Mich M@il program...");
        Utils.sleep(1000);
        System.out.println("Opening port 993 and loading SSL protocol");
        Utils.sleep(600);
        MailWindow mailWindow = (MailWindow) Main.getWindowOfType(Main.WindowType.MAIL);
        if (mailWindow == null) {
            mailWindow = new MailWindow();
        }
        mailWindow.restart();
        System.out.println("Program started successfully");
    }
}
