package ru.volnenko.se.command.data.bin;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IProjectService;
import ru.volnenko.se.api.service.ITaskService;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.constant.DataConstant;
import ru.volnenko.se.entity.Project;
import ru.volnenko.se.entity.Task;
import ru.volnenko.se.event.CommandEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Logger;

/**
 * @author Denis Volnenko
 */
@Component
public final class DataBinaryLoadCommand implements AbstractCommand {

    private static final Logger logger = Logger.getLogger("DataBinaryLoadCommand");

    private final IProjectService projectService;
    private final ITaskService taskService;

    public DataBinaryLoadCommand(IProjectService projectService, ITaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public String command() {
        return "data-bin-load";
    }

    @Override
    public String description() {
        return "Save data to binary file.";
    }

    @Override
    @EventListener(condition = "#event.name eq 'data-bin-load'")
    public void execute(CommandEvent event) throws IOException, ClassNotFoundException {
        logger.info("[DATA BINARY LOAD]");
        final FileInputStream fileInputStream = new FileInputStream(DataConstant.FILE_BINARY);
        try (final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
          loadProjects(objectInputStream.readObject());
          loadTasks(objectInputStream.readObject());
        }
        fileInputStream.close();
        logger.info("[OK]");
    }

    private void loadProjects(final Object value) {
        if (!(value instanceof Project[])) return;
        final Project[] projects = (Project[]) value;
        projectService.load(projects);
    }

    private void loadTasks(final Object value) {
        if (!(value instanceof Task[])) return;
        final Task[] tasks = (Task[]) value;
        taskService.load(tasks);
    }

}
