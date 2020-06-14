package ru.volnenko.se.command.project;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.repository.IProjectRepository;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.event.CommandEvent;

import java.util.logging.Logger;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectClearCommand implements AbstractCommand {

    private static final Logger logger = Logger.getLogger("ProjectClearCommand");

    private final IProjectRepository projectRepository;

    public ProjectClearCommand(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public String command() {
        return "project-clear";
    }

    @Override
    public String description() {
        return "Remove all projects.";
    }

    @Override
    @EventListener(condition = "#event.name eq 'project-clear'")
    public void execute(CommandEvent event) {
        projectRepository.clear();
        logger.info("[ALL PROJECTS REMOVED]");
    }

}
