package ru.volnenko.se.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @author Denis Volnenko
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public final class Project implements Serializable {

    private static final Logger logger = Logger.getLogger("Project");

    private String id = UUID.randomUUID().toString();

    private String name = "";

    private Date dateEnd;

    private Date created = new Date();

    public void test() {
        logger.info("HELLO");
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

}
