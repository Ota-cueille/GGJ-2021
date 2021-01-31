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
    private JButton openFiles;

    public MessageLayout(int list_index, String _user, String _object, String _content, ArrayList<GameFile> _files) {
        this.message = new Message(_user, _object, _content, _files);
        this.x = 0;
        this.y = list_index * this.height;
        this.openMessage = new JButton("open message");
        this.openMessage.setSize(50, 10);
        this.openMessage.addActionListener(this);

        this.openFiles = new JButton("files");
        this.openFiles.setSize(new Dimension(10, 10));
        this.openFiles.addActionListener(this);
    }

    public JPanel getPanel() {
        JPanel panel = new JPanel();
        JLabel lab1 = new JLabel(this.message.getUserName() + ": ");
        lab1.setPreferredSize(new Dimension(200, 10));
        panel.add(lab1);
        JLabel lab2 = new JLabel("\"" + this.message.getObject() + "\"");
        lab2.setPreferredSize(new Dimension(200, 13));
        panel.add(lab2);
        panel.add(openMessage);
        if(this.message.getFileCount() != 0) {
            panel.add(new JLabel(this.message.getFileCount() + " attachment(s)"));
            panel.add(openFiles);
        }
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.openMessage) {
            JOptionPane.showMessageDialog(null, this.message.getMessageContent(),
                    this.message.getObject(), JOptionPane.PLAIN_MESSAGE);
        } else if(e.getSource() == this.openFiles) {
            String buffer = "Do you want to download the given attachments\n";
            buffer += "(Trust the sender ?)";
            int return_value = JOptionPane.showOptionDialog(null, buffer, "attachments", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            if(return_value == JOptionPane.YES_OPTION) {
                this.message.downloadFiles();
            }
        }
    }
}
