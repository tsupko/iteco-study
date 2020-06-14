package ru.volnenko.se.command.system;

import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;

import java.util.List;

/**
 * @author Denis Volnenko
 */
@Component
public final class HelpCommand extends AbstractCommand {

    private final List<? extends AbstractCommand> commands;

    public HelpCommand(List<? extends AbstractCommand> commands) {
        this.commands = commands;
    }

    @Override
    public String command() {
        return "help";
    }

    @Override
    public String description() {
        return "Show all commands.";
    }

    @Override
    public void execute() {
        for (AbstractCommand command: commands) {
            System.out.println(command.command()+ ": " + command.description());
        }
    }

}
