package fr.esaudm.ggj2021.binedit;

import java.io.*;

public class BinaryText {

    private StringBuilder binary;
    private StringBuilder text;

    public void open(File file) {
        this.binary = new StringBuilder();
        this.text = new StringBuilder();
        Reader reader;
        System.out.println(file.getAbsolutePath());
        try {
            reader = new FileReader(file);
            int character;
            while ((character = reader.read()) != -1) {
                this.text.append((char) character);
                if (this.binary.length() != 0) {
                    this.binary.append(" ");
                }
                for (int i = 7; i >= 0; i--) {
                    this.binary.append((character >> i) & 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getText() {
        return this.text.toString();
    }

    public String getBinary() {
        return this.binary.toString();
    }
}
