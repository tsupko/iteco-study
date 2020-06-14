package ru.volnenko.se.command.task;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.ITaskService;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.controller.InputScan;
import ru.volnenko.se.event.CommandEvent;

import java.util.logging.Logger;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskCreateCommand implements AbstractCommand {

    private static final Logger logger = Logger.getLogger("TaskCreateCommand");

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
    @EventListener(condition = "#event.name eq 'task-create'")
    public void execute(CommandEvent event) {
        logger.info("[TASK CREATE]");
        logger.info("ENTER NAME:");
        final String name = inputScan.nextLine();
        taskRepository.createTask(name);
        logger.info("[OK]\n");
    }

}
