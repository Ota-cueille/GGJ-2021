package fr.esaudm.ggj2021.shell;

import java.util.Scanner;

public class Shell {

    private Boolean running = true;

    public Shell() {
        System.out.println("Begining Jean-Mi Shell");
        System.out.println("Type a help to start");
        this.loop();

    }

    public void loop() {
        while (this.running) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            Command cmd = Command.parseCommand(scanner.nextLine());
            if (cmd == null) {
                continue;
            }
            cmd.handle();
            if(cmd.getClass() == QuitCommand.class) {
                this.running = false;
            }
        }
    }
}
