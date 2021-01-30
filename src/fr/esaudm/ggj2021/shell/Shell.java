package fr.esaudm.ggj2021.shell;

import java.util.Scanner;

public class Shell {

    public Shell() {
        System.out.println("Begining Jean-Mi Shell");
        System.out.println("Type help to start");

        this.loop();

    }

    public void loop() {
        while (true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            Command cmd = Command.parseCommand(scanner.nextLine());
            cmd.handle();
        }
    }
}
