package ru.volnenko.se.command.project;

import org.springframework.stereotype.Component;
import ru.volnenko.se.api.repository.IProjectRepository;
import ru.volnenko.se.command.AbstractCommand;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectClearCommand extends AbstractCommand {

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
    public void execute() {
        projectRepository.clear();
        System.out.println("[ALL PROJECTS REMOVED]");
    }

}
