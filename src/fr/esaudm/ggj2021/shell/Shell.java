package fr.esaudm.ggj2021.shell;

import java.io.File;
import java.util.Scanner;

public class Shell {

    private Boolean running = true;
    public static String currentDirectory;

    public Shell() {
        currentDirectory = new File("Game/").getAbsolutePath();
        System.out.println("Begining Jean-Mi Shell");
        System.out.println("Type a command to start (eg : help)");
        this.loop();

    }

    public void loop() {
        while (this.running) {
            System.out.print(currentDirectory + " $ ");
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
