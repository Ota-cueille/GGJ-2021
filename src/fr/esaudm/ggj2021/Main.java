package fr.esaudm.ggj2021;

import fr.esaudm.ggj2021.binedit.BinaryEditorWindow;
import fr.esaudm.ggj2021.binedit.BinaryText;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        /*System.out.println("Begining Jean-Mi Shell");
        System.out.println("Type help to start");
        System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());*/
        //new BinaryEditorWindow();
        BinaryText binaryText = new BinaryText();
        binaryText.open(new File("1234.txt"));
        System.out.println(binaryText.getText());
        System.out.println(binaryText.getBinary());
        new BinaryEditorWindow();
        //Runtime.getRuntime().exec("explorer.exe");
        /*JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);*/
        //new JFrame().setVisible(true);
        //new JFrame().setVisible(true);

    }

}
