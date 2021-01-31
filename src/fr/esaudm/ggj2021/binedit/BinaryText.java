package fr.esaudm.ggj2021.binedit;

import java.io.*;

public class BinaryText {

    private String binary;
    private String text;

    public void open(File file) {
        StringBuilder binaryBuffer = new StringBuilder();
        StringBuilder textBuffer = new StringBuilder();
        Reader reader;
        try {
            reader = new FileReader(file);
            int character;
            while ((character = reader.read()) != -1) {
                textBuffer.append((char) character);
                if (binaryBuffer.length() != 0) {
                    binaryBuffer.append(" ");
                }
                for (int i = 7; i >= 0; i--) {
                    binaryBuffer.append((character >> i) & 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.binary = binaryBuffer.toString();
        this.text = textBuffer.toString();
    }

    public String getText() {
        return this.text;
    }

    public String getBinary() {
        return this.binary;
    }

    public void setText(String text) {
        this.text = text;
        StringBuilder binaryBuffer = new StringBuilder();
        for (char character : this.text.toCharArray()) {
            if (binaryBuffer.length() != 0) {
                binaryBuffer.append(" ");
            }
            for (int i = 7; i >= 0; i--) {
                binaryBuffer.append((character >> i) & 1);
            }
        }
        this.binary = binaryBuffer.toString();
    }

    public void setBinary(String binary) {
        this.binary = binary;
        StringBuilder textBuffer = new StringBuilder();
        byte currentByte = 0;
        for (char character : (this.binary + ' ').toCharArray()) {
            if (character == ' ') {
                textBuffer.append((char) currentByte);
                currentByte = 0;
                continue;
            }
            currentByte = (byte) ((currentByte << 1) + (character == '0' ? 0 : 1));
        }
        this.text = textBuffer.toString();
    }
}
