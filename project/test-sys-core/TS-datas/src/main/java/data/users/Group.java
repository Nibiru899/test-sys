package data.users;

import data.beans.Bean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "group", schema = "public")
@Getter @Setter
public class Group extends Bean {
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;

}
