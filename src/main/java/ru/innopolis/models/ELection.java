package ru.innopolis.models;

import javax.persistence.*;

/**
 * Created by Alexander Chuvashov on 01.12.2016.
 */

@Entity
@Table(name = "lections")
public class ELection {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="lections_id_seq",
            sequenceName="lections_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="lections_id_seq")
    int id;

    @Column(name = "date")
    String date;

    @Column(name = "theme")
    String theme;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTheme() {
        return theme;
    }

    public ELection(int id, String date, String theme) {
        super();
        this.id = id;
        this.date = date;
        this.theme = theme;
    }

    public ELection(String date, String theme) {
        super();
        this.date = date;
        this.theme = theme;
    }

    public ELection() {
        super();
    }


}

