package fr.esaudm.ggj2021;

import fr.esaudm.ggj2021.binedit.BinaryText;

public class Test {

    public static void main(String[] args) {
        StringBuilder str = new StringBuilder();
        /*Random random = new Random();
        for (int i = 0; i < 10; i++) {
            str.append((char) random.nextInt(256));
        }*/
        /*str.append("menna.google");
        while (str.length() < 16) {
            str.append((char) 0);
        }*/
        BinaryText txt = new BinaryText();
        txt.setText(str.toString());
        System.out.println(txt.getBinary());

    }

}
