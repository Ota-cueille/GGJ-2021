package fr.esaudm.ggj2021.mail;

import fr.esaudm.ggj2021.Main;
import fr.esaudm.ggj2021.folderSystem.GameFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MailWindow extends JPanel {

    private JFrame mailWindow;
    private ArrayList<JSONObject> user_message_data;
    private HashMap<Integer, Boolean> notLoaded;
    private ArrayList<MessageLayout> messages;

    public MailWindow() {
        this.notLoaded = new HashMap<Integer, Boolean>();
        this.user_message_data = new ArrayList<JSONObject>();
        this.messages = new ArrayList<MessageLayout>();
        this.load();
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
        
        this.newMessageReceived(0);
        this.notLoaded.put(0, false);
        
        for(int j = 1; j < this.user_message_data.size(); j++) {
            notLoaded.put(j, true);
        }
    }

    private void load() {
        JSONParser parser = new JSONParser();
        try(FileReader fileReader = new FileReader("Assets/Mail/Manager.json")) {
            try {
                JSONObject json_file = (JSONObject) parser.parse(fileReader);
                JSONArray users_messages = (JSONArray) json_file.get("user_list");
                for(Object data: users_messages) {
                    // load a user message in the mailbox
                    this.user_message_data.add((JSONObject)data);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newMessageReceived(int index) {
        JSONArray files = (JSONArray) user_message_data.get(index).get("files");
        ArrayList<GameFile> gameFiles = new ArrayList<GameFile>();
        if(files != null) {
            for(Object file : files) {
                String obj = (String) file;
                gameFiles.add(new GameFile(obj));
            }
        }
        this.messages.add(new MessageLayout(index,
                (String) user_message_data.get(index).get("name"),
                (String) user_message_data.get(index).get("object"),
                (String) user_message_data.get(index).get("content"),
                gameFiles
        ));
        this.notLoaded.put(index, Boolean.FALSE);
        this.updatePanel();
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
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    new File("Assets/Sound/wilhelm.wav"));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mailWindow.setVisible(true);
    }
    
    public Boolean hasNotBeenLoaded(int index) {
        return (this.notLoaded.get(index) && index < this.user_message_data.size());
    }
}
