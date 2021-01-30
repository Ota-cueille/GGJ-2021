package fr.esaudm.ggj2021.mail;

import fr.esaudm.ggj2021.folderSystem.GameFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MessageLayout implements ActionListener {

    private int x, y;
    private final int width = 300, height = 30;
    private final int max_name_size = 10;
    private Message message;
    private JButton openMessage;

    public MessageLayout(int list_index, String _user, String _object, String _content, ArrayList<GameFile> _files) {
        this.message = new Message(_user, _object, _content, _files);
        this.x = 0;
        this.y = list_index * this.height;
        JLabel label = new JLabel();
        this.openMessage = new JButton("open message");
        this.openMessage.setSize(50, 10);
        this.openMessage.addActionListener(this);
    }

    public JPanel getPanel() {
        JPanel panel = new JPanel();
        JLabel lab1 = new JLabel(this.message.getUserName() + ": ");
        lab1.setPreferredSize(new Dimension(80, 10));
        panel.add(lab1);
        JLabel lab2 = new JLabel("\"" + this.message.getObject() + "\"");
        lab2.setPreferredSize(new Dimension(200, 13));
        panel.add(lab2);
        panel.add(openMessage);
        if(this.message.getFileCount() != 0) {
            panel.add(new JLabel("Pièces Jointes: " + this.message.getFileCount()));
        }
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane d = new JOptionPane();
        d.showMessageDialog( null, this.message.getMessageContent(),
                this.message.getObject(), JOptionPane.PLAIN_MESSAGE);
    }
}
