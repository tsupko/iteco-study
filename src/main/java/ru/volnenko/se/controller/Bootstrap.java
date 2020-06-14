package ru.volnenko.se.controller;

import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Denis Volnenko
 */
@Component
public final class Bootstrap {

    private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();
    private InputScan scanner;

    public Bootstrap(List<? extends AbstractCommand> commands, InputScan scanner) {
        for (AbstractCommand command : commands) {
            this.commands.put(command.command(), command);
        }
        this.scanner = scanner;
    }

    public void start() throws Exception {
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        String command = "";
        while (!"exit".equals(command)) {
            command = scanner.nextLine();
            execute(command);
        }
    }

    private void execute(final String command) throws Exception {
        if (command == null || command.isEmpty()) return;
        final AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null) return;
        abstractCommand.execute();
    }

}
