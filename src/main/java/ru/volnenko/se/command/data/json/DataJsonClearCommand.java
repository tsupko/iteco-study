package ru.volnenko.se.command.data.json;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.constant.DataConstant;
import ru.volnenko.se.event.CommandEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Denis Volnenko
 */
@Component
public final class DataJsonClearCommand implements AbstractCommand {

    @Override
    public String command() {
        return "data-json-clear";
    }

    @Override
    public String description() {
        return "Remove JSON file.";
    }

    @Override
    @EventListener(condition = "#event.name eq 'data-json-clear'")
    public void execute(CommandEvent event) throws IOException {
        final File file = new File(DataConstant.FILE_JSON);
        Files.deleteIfExists(file.toPath());
    }

}
