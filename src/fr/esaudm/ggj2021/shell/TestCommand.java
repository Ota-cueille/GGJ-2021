package fr.esaudm.ggj2021.shell;

import fr.esaudm.ggj2021.Main;
import fr.esaudm.ggj2021.reminder.ReminderWindow;

public class TestCommand extends Command{
    @Override
    public void handle() {
        ((ReminderWindow) Main.getWindowOfType(Main.WindowType.REMINDER)).addTrojanHorse();
    }
}
