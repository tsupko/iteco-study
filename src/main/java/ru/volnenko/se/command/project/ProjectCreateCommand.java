package ru.volnenko.se.command.project;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.repository.IProjectRepository;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.controller.InputScan;
import ru.volnenko.se.event.CommandEvent;

import java.util.logging.Logger;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectCreateCommand implements AbstractCommand {

    private static final Logger logger = Logger.getLogger("ProjectCreateCommand");

    private final InputScan inputScan;
    private final IProjectRepository projectRepository;

    public ProjectCreateCommand(InputScan inputScan, IProjectRepository projectRepository) {
        this.inputScan = inputScan;
        this.projectRepository = projectRepository;
    }

    @Override
    public String description() {
        return "Create new project.";
    }

    @Override
    public String command() {
        return "project-create";
    }

    @Override
    @EventListener(condition = "#event.name eq 'project-create'")
    public void execute(CommandEvent event) {
        logger.info("[PROJECT CREATE]");
        logger.info("ENTER NAME:");
        final String name = inputScan.nextLine();
        projectRepository.createProject(name);
        logger.info("[OK]\n");
    }

}
