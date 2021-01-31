package fr.esaudm.ggj2021.shell;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Command {

    private static final List<Function<String, Command>> commandParsers = new ArrayList<>();

    static {
        Command.addCommandParser(cmd -> cmd.startsWith("mail") ? new MailCommand() : null);
        Command.addCommandParser(cmd -> cmd.startsWith("binedit") ? new BinEditCommand() : null);
        Command.addCommandParser(cmd -> cmd.startsWith("notepad") ? new NotepadCommand() : null);
        Command.addCommandParser(cmd -> cmd.startsWith("help") ? new HelpCommand() : null);
        Command.addCommandParser(cmd -> cmd.startsWith("tutorial") ? new TutorialCommand() : null);
        Command.addCommandParser(cmd -> cmd.startsWith("quit") ? new QuitCommand() : null);
        Command.addCommandParser(cmd -> cmd.startsWith("exit") ? new QuitCommand() : null);
        Command.addCommandParser(cmd -> cmd.startsWith("push") ? new PushCommand(cmd) : null);
        Command.addCommandParser(cmd -> cmd.startsWith("cd") ? new ChangeDirectoryCommand(cmd) : null);
        Command.addCommandParser(cmd -> cmd.startsWith("ls") ? new ListFilesCommand() : null);
        Command.addCommandParser(cmd -> cmd.startsWith("openpdf") ? new OpenPDFCommand(cmd) : null);
    }

    private static void addCommandParser(Function<String, Command> parser) {
        Command.commandParsers.add(parser);
    }

    public static Command parseCommand(String cmd) {
        if (cmd.isBlank()) {
            return null;
        }
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
