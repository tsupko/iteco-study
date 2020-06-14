package ru.volnenko.se.command.task;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.repository.ITaskRepository;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.event.CommandEvent;

import java.util.logging.Logger;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskClearCommand implements AbstractCommand {

    private static final Logger logger = Logger.getLogger("TaskClearCommand");

    private final ITaskRepository taskRepository;

    public TaskClearCommand(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public String description() {
        return "Remove all tasks.";
    }

    @Override
    public String command() {
        return "task-clear";
    }

    @Override
    @EventListener(condition = "#event.name eq 'task-clear'")
    public void execute(CommandEvent event) {
        taskRepository.clear();
        logger.info("[ALL TASK REMOVED]");
    }

}
