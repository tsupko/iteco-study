package ru.volnenko.se.command.system;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.event.CommandEvent;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Denis Volnenko
 */
@Component
public final class HelpCommand implements AbstractCommand {

    private static final Logger logger = Logger.getLogger("HelpCommand");

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
    @EventListener(condition = "#event.name eq 'help'")
    public void execute(CommandEvent event) {
        for (AbstractCommand command: commands) {
            logger.info(() -> command.command() + ": " + command.description());
        }
    }

}
