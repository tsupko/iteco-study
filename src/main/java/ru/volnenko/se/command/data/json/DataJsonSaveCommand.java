package ru.volnenko.se.command.data.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IDomainService;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.constant.DataConstant;
import ru.volnenko.se.entity.Domain;
import ru.volnenko.se.event.CommandEvent;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.logging.Logger;

/**
 * @author Denis Volnenko
 */
@Component
public final class DataJsonSaveCommand implements AbstractCommand {

    private static final Logger logger = Logger.getLogger("DataJsonSaveCommand");

    private final IDomainService domainService;

    public DataJsonSaveCommand(IDomainService domainService) {
        this.domainService = domainService;
    }

    @Override
    public String command() {
        return "data-json-save";
    }

    @Override
    public String description() {
        return "Save Domain to JSON.";
    }

    @Override
    @EventListener(condition = "#event.name eq 'data-json-save'")
    public void execute(CommandEvent event) throws IOException {
        logger.info("[DATA JSON SAVE]");
        final Domain domain = new Domain();
        domainService.export(domain);
        final ObjectMapper objectMapper = new ObjectMapper();
        final ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        final String json = objectWriter.writeValueAsString(domain);
        final byte[] data = json.getBytes(StandardCharsets.UTF_8);
        final File file = new File(DataConstant.FILE_JSON);
        Files.write(file.toPath(), data);
        logger.info("[OK]");
    }

}
