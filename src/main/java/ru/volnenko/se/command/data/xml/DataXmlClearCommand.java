package ru.volnenko.se.command.data.xml;

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
public final class DataXmlClearCommand implements AbstractCommand {

    @Override
    public String command() {
        return "data-xml-clear";
    }

    @Override
    public String description() {
        return "Remove XML file.";
    }

    @Override
    @EventListener(condition = "#event.name eq 'data-xml-clear'")
    public void execute(CommandEvent event) throws IOException {
        final File file = new File(DataConstant.FILE_XML);
        Files.deleteIfExists(file.toPath());
    }

}
