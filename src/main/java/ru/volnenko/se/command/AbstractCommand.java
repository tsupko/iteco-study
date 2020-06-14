package ru.volnenko.se.command;

import ru.volnenko.se.event.CommandEvent;

import java.io.IOException;

/**
 * @author Denis Volnenko
 */
public interface AbstractCommand {

    void execute(CommandEvent event) throws IOException, ClassNotFoundException;

    String command();

    String description();

}
