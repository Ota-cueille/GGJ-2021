package fr.esaudm.ggj2021.utils;

public class Utils {

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
