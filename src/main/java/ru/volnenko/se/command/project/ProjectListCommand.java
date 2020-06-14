package ru.volnenko.se.command.project;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IProjectService;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.entity.Project;
import ru.volnenko.se.event.CommandEvent;

import java.util.logging.Logger;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectListCommand implements AbstractCommand {

    private static final Logger logger = Logger.getLogger("ProjectListCommand");

    private final IProjectService projectService;

    public ProjectListCommand(IProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public String command() {
        return "project-list";
    }

    @Override
    public String description() {
        return "Show all projects.";
    }

    @Override
    @EventListener(condition = "#event.name eq 'project-list'")
    public void execute(CommandEvent event) {
        logger.info("[PROJECT LIST]");
        final int[] index = {1};
        for (Project project: projectService.getListProject()) {
          logger.info(() -> index[0]++ + ". " + project.getName());
        }
        logger.info("\n");
    }

}
