package ru.volnenko.se.command.data.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IDomainService;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.constant.DataConstant;
import ru.volnenko.se.entity.Domain;

import java.io.File;
import java.nio.file.Files;

/**
 * @author Denis Volnenko
 */
@Component
public final class DataJsonSaveCommand extends AbstractCommand {

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
    public void execute() throws Exception {
        System.out.println("[DATA JSON SAVE]");
        final Domain domain = new Domain();
        domainService.export(domain);
        final ObjectMapper objectMapper = new ObjectMapper();
        final ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        final String json = objectWriter.writeValueAsString(domain);
        final byte[] data = json.getBytes("UTF-8");
        final File file = new File(DataConstant.FILE_JSON);
        Files.write(file.toPath(), data);
        System.out.println("[OK]");
    }

}
