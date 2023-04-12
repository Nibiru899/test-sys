package data.users;

import data.beans.Bean;
import javax.persistence.*;

@Entity
@Table(name = "group", schema = "public")
public class Group extends Bean {
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
