package fr.esaudm.ggj2021.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import fr.esaudm.ggj2021.folderSystem.GameFile;

public class Message {

    private final String user_name;
    private final String object;
    private String content;
    private final ArrayList<GameFile> files;

    public Message(String _user, String _object, String _content, ArrayList<GameFile> _files) {
        this.user_name = _user;
        this.object = _object;
        this.content = new String();
        try {
            File user_message = new File(_content);
            Scanner reader = new Scanner(user_message);
            while (reader.hasNext()) {
                this.content += reader.nextLine();
                this.content += "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.files = _files;
    }

    public String getUserName() {
        return this.user_name;
    }

    public String getObject() {
        return this.object;
    }

    public String getMessageContent() {
        return this.content;
    }

    public int getFileCount() {
        return this.files.size();
    }

    public void downloadFiles() {
        for(GameFile file : this.files) {
            file.move("Game/Downloads/");
        }
    }
}
