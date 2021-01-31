package fr.esaudm.ggj2021.mail;

import fr.esaudm.ggj2021.Main;
import fr.esaudm.ggj2021.folderSystem.GameFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MailWindow extends JPanel {

    private JFrame mailWindow;

    private ArrayList<MessageLayout> messages;

    public MailWindow() {
        this.messages = new ArrayList<MessageLayout>();
        JSONParser parser = new JSONParser();
        try(FileReader fileReader = new FileReader("Assets/Mail/Manager.json")) {
            try {
                JSONObject json_file = (JSONObject) parser.parse(fileReader);
                JSONArray users_messages = (JSONArray) json_file.get("user_list");
                int iterator = 0;
                for(Object data: users_messages) {
                    JSONObject message_data = (JSONObject) data;
                    ArrayList<GameFile> gameFiles = new ArrayList<GameFile>();
                    JSONArray files = (JSONArray) message_data.get("files");
                    if(files != null) {
                        for(Object file : files) {
                            String obj = (String) file;
                            gameFiles.add(new GameFile(obj));
                        }
                    }
                    this.messages.add(new MessageLayout(iterator,
                            (String) message_data.get("name"),
                            (String) message_data.get("object"),
                            (String) message_data.get("content"),
                            gameFiles
                    ));
                    iterator++;
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.mailWindow = new JFrame();
        this.mailWindow.addWindowListener(new Main.WindowClosingHandler(this));
        this.mailWindow.setSize(new Dimension(600, 300));
        this.mailWindow.setTitle("St-Mich M@il");
        try{
            this.mailWindow.setIconImage(ImageIO.read(new File("Assets/Image/mt_st_mich_mail.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mailWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mailWindow.setContentPane(this);
        this.mailWindow.setContentPane(new JScrollPane(this));

        this.updatePanel();

        this.mailWindow.setVisible(true);
    }

    private void updatePanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        for (int i = 0; i < messages.size(); i++) {
            gbc.gridy = i;
            this.add(this.messages.get(i).getPanel(), gbc);
        }
    }
}
