package ru.volnenko.se.command.task;

import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.ITaskService;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.entity.Task;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskListCommand extends AbstractCommand {

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
    public void execute() {
        System.out.println("[TASK LIST]");
        int index = 1;
        for (Task task: taskRepository.getListTask()) {
            System.out.println(index + ". " + task.getName());
            index++;
        }
        System.out.println();
    }

}
