package ru.volnenko.se.command.project;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.event.CommandEvent;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectRemoveCommand implements AbstractCommand {

    @Override
    public String command() {
        return "project-remove";
    }

    @Override
    public String description() {
        return "Remove selected project.";
    }

    @Override
    @EventListener(condition = "#event.name eq 'project-remove'")
    public void execute(CommandEvent event) {
        throw new UnsupportedOperationException();
    }

}
