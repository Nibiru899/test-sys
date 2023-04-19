package data.service;

import data.beans.Bean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "picture")
@Getter @Setter
public class Picture extends Bean {
    @Column(name = "filename")
    private String fileName;
    @Column(name = "name")
    private String name;

}
