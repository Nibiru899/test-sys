package data.service;

import data.beans.Bean;
import javax.persistence.*;

@Entity
@Table(name = "picture")
public class Picture extends Bean {
    @Column(name = "filename")
    private String fileName;
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
