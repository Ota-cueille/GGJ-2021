package fr.esaudm.ggj2021.shell;

import fr.esaudm.ggj2021.mail.MailWindow;
import fr.esaudm.ggj2021.reminder.ReminderWindow;
import fr.esaudm.ggj2021.utils.Utils;

public class NotepadCommand extends Command {

    @Override
    public void handle() {
        System.out.println("Loading notepad program...");
        Utils.sleep(1000);
        System.out.println("Starting OpenGL shaders...");
        Utils.sleep(600);
        new ReminderWindow();
        System.out.println("Program started successfully");
    }

}
