package data.users;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@DiscriminatorValue("student")
@Getter @Setter
public class Student extends User{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

}
