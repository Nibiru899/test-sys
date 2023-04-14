package data.users;

import data.beans.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "password")
public class Password extends Bean {
    Password(){

    }

    @Column(name = "text")
    private String text;

    public Password(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
