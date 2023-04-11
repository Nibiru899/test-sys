package data.users;

import javax.persistence.*;
@Entity
@DiscriminatorValue("student")
public class Student extends User{
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
