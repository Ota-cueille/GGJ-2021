package fr.esaudm.ggj2021.reminder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class ReminderWindow extends JPanel implements MouseListener {

    private JFrame reminderWindow;
    private final HashMap<JTextArea, JScrollPane> shitty;
    private int maximum = 0;

    public ReminderWindow() {
        this.shitty = new HashMap<JTextArea, JScrollPane>();
        this.reminderWindow = new JFrame();
        this.reminderWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.reminderWindow.setSize(new Dimension(800, 600));
        this.reminderWindow.setContentPane(this);
        this.reminderWindow.setVisible(true);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3 && e.getSource() == this && maximum < 25) {
            JTextArea textArea = new JTextArea(7, 20);
            textArea.setLineWrap(true);
            textArea.addMouseListener(this);
            JScrollPane sp = new JScrollPane(textArea);
            this.add(sp);
            this.shitty.put(textArea, sp);
            this.reminderWindow.setVisible(true);
            maximum++;
        } else if(e.getButton() == MouseEvent.BUTTON2 && e.getSource() != this) {
            JTextArea ta = (JTextArea) e.getSource();
            this.remove(ta);
            this.remove(this.shitty.get(ta));
            this.shitty.remove(ta);
            this.setVisible(false);
            this.setVisible(true);
            maximum--;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
