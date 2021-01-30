package fr.esaudm.ggj2021.binedit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame {

    private JTextArea textPane = new JTextArea();
    private JScrollPane scroll = new JScrollPane(textPane);

    public Test(){
        this.setLocationRelativeTo(null);
        this.setTitle("Gérer vos conteneur");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200, 200);

        //On ajoute l'objet au content pane de notre fenêtre
        this.getContentPane().add(new JScrollPane(textPane), BorderLayout.CENTER);
        //On aurait pu aussi écrire
        //this.getContentPane().add(new JScrollPane(textPane), BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void main(String[] args){
        Test fen = new Test();
    }

}
