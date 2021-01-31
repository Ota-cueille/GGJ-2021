package fr.esaudm.ggj2021.reminder;

import fr.esaudm.ggj2021.Main;
import fr.esaudm.ggj2021.shell.Shell;

import javax.imageio.ImageIO;
import javax.sound.midi.spi.SoundbankReader;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JMJTrojanHorse extends JPanel {

    private BufferedImage horse;
    private BufferedImage jmj;
    private BufferedImage message;
    private ReminderWindow window;
    private int currentX = 370;

    public JMJTrojanHorse(JFrame frame, ReminderWindow window) {
        try {
            this.horse = ImageIO.read(new File("Assets/Image/Horse.png"));
            this.jmj = ImageIO.read(new File("Assets/Image/jmj.png"));
            this.message = ImageIO.read(new File("Assets/Image/trojan_message.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setContentPane(this);
        this.window = window;
        frame.validate();
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    new File("Assets/Sound/virus_detected.wav"));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        float rotation = (float) Math.toRadians(90);
        int END_X = 800;
        if (this.currentX > END_X) {
            rotation = (float) Math.toRadians(90 - this.currentX + END_X);
        }
        AffineTransform tx = AffineTransform.getRotateInstance(rotation, Math.min(this.currentX, END_X) + this.jmj.getWidth() / 20f, 150 + this.jmj.getHeight() / 20f);
        AffineTransform defaultTransform = graphics.getTransform();
        graphics.setTransform(tx);
        graphics.drawImage(this.jmj, Math.min(this.currentX, END_X), 150, this.jmj.getWidth() / 10, this.jmj.getHeight() / 10, null);
        graphics.setTransform(defaultTransform);
        graphics.drawImage(this.horse, 0, 0, null);
        if (this.currentX >= 90 + END_X + 360 * 3) {
            graphics.drawImage(this.message, 400, 200, this.message.getWidth() / 3, this.message.getHeight() / 3, null);
        }
        this.currentX++;
        if (this.currentX <= 90 + END_X + 360 * 3) {
            this.repaint();
        }
    }
}
