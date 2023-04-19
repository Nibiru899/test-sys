package data.forming.Themes;

import data.beans.Bean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "competity")
@Getter @Setter
public class Competity extends Bean {
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
