package ru.volnenko.se.command.data.json;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public final class DataJsonLoadCommand implements AbstractCommand {

    private static final Logger logger = Logger.getLogger("DataJsonLoadCommand");

    private final IDomainService domainService;

    public DataJsonLoadCommand(IDomainService domainService) {
        this.domainService = domainService;
    }

    @Override
    public String command() {
        return "data-json-load";
    }

    @Override
    public String description() {
        return "Load Domain from JSON.";
    }

    @Override
    @EventListener(condition = "#event.name eq 'data-json-load'")
    public void execute(CommandEvent event) throws IOException {
        logger.info("[LOAD JSON DATA]");
        final File file = new File(DataConstant.FILE_JSON);
        if (!exists(file)) return;
        final byte[] bytes = Files.readAllBytes(file.toPath());
        final String json = new String(bytes, StandardCharsets.UTF_8);
        final ObjectMapper objectMapper = new ObjectMapper();
        final Domain domain = objectMapper.readValue(json, Domain.class);
        domainService.load(domain);
        logger.info("[OK]");
    }

    private boolean exists(final File file) {
        if (file == null) return false;
        final boolean check = file.exists();
        if (!check) logger.warning("FILE NOT FOUND");
        return check;
    }

}
