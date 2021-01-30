package fr.esaudm.ggj2021.shell;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Command {

    private static final List<Function<String, Command>> commandParsers = new ArrayList<>();

    static {
        Command.addCommandParser(cmd -> cmd.startsWith("mail") ? new MailCommand() : null);
    }

    private static void addCommandParser(Function<String, Command> parser) {
        Command.commandParsers.add(parser);
    }

    public static Command parseCommand(String cmd) {
        for (Function<String, Command> parser : Command.commandParsers) {
            Command commandObj = parser.apply(cmd);
            if (commandObj != null) {
                return commandObj;
            }
        }
        return new UnknownCommand();
    }

    public abstract void handle();

}
