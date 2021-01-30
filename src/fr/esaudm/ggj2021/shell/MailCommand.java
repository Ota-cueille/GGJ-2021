package fr.esaudm.ggj2021.shell;

import fr.esaudm.ggj2021.mail.MailWindow;

public class MailCommand extends Command {

    @Override
    public void handle() {
        System.out.println("Starting Jean-Mich M@il program...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new MailWindow();
        System.out.println("Program started successfully");
    }
}
