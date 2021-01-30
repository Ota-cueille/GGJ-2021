package fr.esaudm.ggj2021.shell;

public class UnknownCommand extends Command {

    @Override
    public void handle() {
        System.out.println("This command does not exist. Type help to get a list of commands");
    }
}
