package fr.esaudm.ggj2021;
import fr.esaudm.ggj2021.binedit.BinaryEditorWindow;
import fr.esaudm.ggj2021.mail.MailWindow;
import fr.esaudm.ggj2021.reminder.ReminderWindow;
import fr.esaudm.ggj2021.shell.Shell;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<Object> windows = new ArrayList<>();

    public static void main(String[] args) {
        new Shell();
    }

    public static void addWindow(Object window) {
        Main.windows.add(window);
    }

    public static Object getWindowOfType(WindowType type) {
        for (Object window : Main.windows) {
            if (window.getClass() == type.clazz) {
                return window;
            }
        }
        return null;
    }

    public static void removeWindow(Object window) {
        Main.windows.remove(window);
    }

    public enum WindowType {
        BINARY_EDITOR(BinaryEditorWindow.class),
        MAIL(MailWindow.class),
        REMINDER(ReminderWindow.class);
        public final Class<?> clazz;
        WindowType(Class<?> clazz) {
            this.clazz = clazz;
        }
    }

    public static class WindowClosingHandler implements WindowListener {
        private final Object window;
        public WindowClosingHandler(Object window) {
            this.window = window;
        }
        @Override
        public void windowOpened(WindowEvent e) {
            Main.addWindow(this.window);
        }
        @Override
        public void windowClosed(WindowEvent e) {
            Main.removeWindow(this.window);
        }
        @Override
        public void windowClosing(WindowEvent e) { }
        @Override
        public void windowIconified(WindowEvent e) { }
        @Override
        public void windowDeiconified(WindowEvent e) { }
        @Override
        public void windowActivated(WindowEvent e) { }
        @Override
        public void windowDeactivated(WindowEvent e) { }
    }
}
