package data.users;

import data.beans.Bean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "password")
@Getter @Setter
public class Password extends Bean {
    Password(){

    }

    @Column(name = "text")
    private String text;

    public Password(String text) {
        this.text = text;
    }


}
