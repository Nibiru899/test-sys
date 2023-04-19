package data.users;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import data.beans.Bean;
import data.forming.Questions.ConnectingQuestions;
import data.forming.Questions.SimpleQuestion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "disc")
@Getter @Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Teacher.class, name = "Teacher"),

        @JsonSubTypes.Type(value = Student.class, name = "Student") }
)
public class User extends Bean {
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surName;
    @Column(name = "fathername")
    private String fatherName;
    @Column(name = "login")
    private String login;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password")
    private Password password;

}
