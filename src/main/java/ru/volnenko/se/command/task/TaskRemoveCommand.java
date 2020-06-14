package ru.volnenko.se.command.task;

import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.controller.InputScan;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskRemoveCommand extends AbstractCommand {

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
    public void execute() {
        System.out.println("[REMOVING TASK]");
        System.out.println("Enter task order index:");
        final Integer orderIndex = inputScan.nextInteger();
        if (orderIndex == null) {
            System.out.println("Error! Incorrect order index...");
            System.out.println();
            return;
        }
        System.out.println("[OK]");
    }

}
