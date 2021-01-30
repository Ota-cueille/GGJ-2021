package fr.esaudm.ggj2021.shell;

public class QuitCommand extends Command {
    @Override
    public void handle() {
        System.out.println("au revoir !");
    }
}
