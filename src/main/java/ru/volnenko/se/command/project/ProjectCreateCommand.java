package ru.volnenko.se.command.project;

import org.springframework.stereotype.Component;
import ru.volnenko.se.api.repository.IProjectRepository;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.controller.InputScan;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectCreateCommand extends AbstractCommand {

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
    public void execute() {
        System.out.println("[PROJECT CREATE]");
        System.out.println("ENTER NAME:");
        final String name = inputScan.nextLine();
        projectRepository.createProject(name);
        System.out.println("[OK]");
        System.out.println();
    }

}
