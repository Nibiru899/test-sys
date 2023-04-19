package data.users;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("teacher")
@Getter @Setter
public class Teacher extends User {
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Group> groups;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Subject> subjects;

}
