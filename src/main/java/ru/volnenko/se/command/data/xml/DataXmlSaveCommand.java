package ru.volnenko.se.command.data.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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
public final class DataXmlSaveCommand implements AbstractCommand {

    private static final Logger logger = Logger.getLogger("DataXmlSaveCommand");

    private final IDomainService domainService;

    public DataXmlSaveCommand(IDomainService domainService) {
        this.domainService = domainService;
    }

    @Override
    public String command() {
        return "data-xml-save";
    }

    @Override
    public String description() {
        return "Save Domain to XML.";
    }

    @Override
    @EventListener(condition = "#event.name eq 'data-xml-save'")
    public void execute(CommandEvent event) throws IOException {
        logger.info("[DATA XML SAVE]");
        final Domain domain = new Domain();
        domainService.export(domain);
        final ObjectMapper objectMapper = new XmlMapper();
        final ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        final String json = objectWriter.writeValueAsString(domain);
        final byte[] data = json.getBytes(StandardCharsets.UTF_8);
        final File file = new File(DataConstant.FILE_XML);
        Files.write(file.toPath(), data);
        logger.info("[OK]");
    }

}
