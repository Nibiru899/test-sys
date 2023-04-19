package data.forming.Plans;

import data.beans.Bean;
import data.forming.Themes.Theme;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "themePersist")
@Getter @Setter
public class ThemePersist extends Bean {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme")
    private Theme theme;

    @Column(name = "count")
    private Integer count;
}
