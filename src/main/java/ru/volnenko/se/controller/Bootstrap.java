package ru.volnenko.se.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import ru.volnenko.se.event.CommandEvent;

import java.util.logging.Logger;

/**
 * @author Denis Volnenko
 */
@Component
public final class Bootstrap {

    private static final Logger logger = Logger.getLogger("Bootstrap");

    private final InputScan scanner;
    private final ApplicationEventPublisher publisher;

    public Bootstrap(InputScan scanner, ApplicationEventPublisher publisher) {
        this.scanner = scanner;
        this.publisher = publisher;
    }

    public void start() {
        logger.info("*** WELCOME TO TASK MANAGER ***");
        String command = "";
        while (!"exit".equals(command)) {
            command = scanner.nextLine();
            execute(command);
        }
    }

    private void execute(final String command) {
        final CommandEvent commandEvent = new CommandEvent(command);
        publisher.publishEvent(commandEvent);
    }

}
