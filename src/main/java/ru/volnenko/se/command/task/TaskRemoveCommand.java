package ru.volnenko.se.command.task;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.controller.InputScan;
import ru.volnenko.se.event.CommandEvent;

import java.util.logging.Logger;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskRemoveCommand implements AbstractCommand {

    private static final Logger logger = Logger.getLogger("TaskRemoveCommand");

    private final InputScan inputScan;

    public TaskRemoveCommand(InputScan inputScan) {
        this.inputScan = inputScan;
    }

    @Override
    public String command() {
        return "task-remove";
    }

    @Override
    public String description() {
        return "Remove selected task.";
    }

    @Override
    @EventListener(condition = "#event.name eq 'task-remove'")
    public void execute(CommandEvent event) {
        logger.info("[REMOVING TASK]");
        logger.info("Enter task order index:");
        final Integer orderIndex = inputScan.nextInteger();
        if (orderIndex == null) {
            logger.warning("Error! Incorrect order index...\n");
            return;
        }
        logger.info("[OK]");
    }

}
