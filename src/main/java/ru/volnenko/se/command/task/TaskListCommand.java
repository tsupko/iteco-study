package ru.volnenko.se.command.task;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.ITaskService;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.entity.Task;
import ru.volnenko.se.event.CommandEvent;

import java.util.logging.Logger;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskListCommand implements AbstractCommand {

    private static final Logger logger = Logger.getLogger("TaskListCommand");

    private final ITaskService taskRepository;

    public TaskListCommand(ITaskService taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public String command() {
        return "task-list";
    }

    @Override
    public String description() {
        return "Show all tasks.";
    }

    @Override
    @EventListener(condition = "#event.name eq 'task-list'")
    public void execute(CommandEvent event) {
        logger.info("[TASK LIST]");
        final int[] index = {1};
        for (Task task: taskRepository.getListTask()) {
            logger.info(() -> index[0]++ + ". " + task.getName());
        }
        logger.info("\n");
    }

}
