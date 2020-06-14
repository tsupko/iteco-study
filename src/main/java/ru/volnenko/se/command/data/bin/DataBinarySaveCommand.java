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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.logging.Logger;

/**
 * @author Denis Volnenko
 */
@Component
public final class DataBinarySaveCommand implements AbstractCommand {

    private static final Logger logger = Logger.getLogger("DataBinarySaveCommand");

    private final IProjectService projectService;
    private final ITaskService taskService;

    public DataBinarySaveCommand(IProjectService projectService, ITaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public String command() {
        return "data-bin-save";
    }

    @Override
    public String description() {
        return "Load data from binary file.";
    }

    @Override
    @EventListener(condition = "#event.name eq 'data-bin-save'")
    public void execute(CommandEvent event) throws IOException {
        logger.info("[DATA BINARY SAVE]");
        final Project[] projects = projectService.getListProject().toArray(new Project[] {});
        final Task[] tasks = taskService.getListTask().toArray(new Task[] {});

        final File file = new File(DataConstant.FILE_BINARY);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());

        final FileOutputStream fileOutputStream = new FileOutputStream(file);
        try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
          objectOutputStream.writeObject(projects);
          objectOutputStream.writeObject(tasks);
        }
        fileOutputStream.close();

        logger.info("[OK]\n");
    }

}
