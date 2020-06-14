package ru.volnenko.se.command.task;

import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.ITaskService;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.controller.InputScan;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskCreateCommand extends AbstractCommand {

    private final ITaskService taskRepository;
    private final InputScan inputScan;

    public TaskCreateCommand(ITaskService taskRepository, InputScan inputScan) {
        this.taskRepository = taskRepository;
        this.inputScan = inputScan;
    }

    @Override
    public String command() {
        return "task-create";
    }

    @Override
    public String description() {
        return "Create new task.";
    }

    @Override
    public void execute() {
        System.out.println("[TASK CREATE]");
        System.out.println("ENTER NAME:");
        final String name = inputScan.nextLine();
        taskRepository.createTask(name);
        System.out.println("[OK]");
        System.out.println();
    }

}
