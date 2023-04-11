package data.users;

import javax.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("teacher")
public class Teacher extends User {
    @ManyToMany
    private List<Group> groups;

    @ManyToMany
    private List<Subject> subjects;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
