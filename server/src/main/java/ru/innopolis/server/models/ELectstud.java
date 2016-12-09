package ru.innopolis.server.models;

import javax.persistence.*;

/**
 * Created by Alexander Chuvashov on 09.12.2016.
 */

@Entity
@Table(name = "lectstud")
public class ELectstud {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name="lectstud_id_seq",
            sequenceName="lectstud_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="lectstud_id_seq")
    private int id;

    @Column(name = "lectionid")
    private int lectionid;

    @Column(name = "studentid")
    private int studentid;

    public ELectstud() {
        super();
    }

    public ELectstud(int lectionid, int studentid) {
        super();
        this.lectionid = lectionid;
        this.studentid = studentid;
    }

    public int getId() {
        return id;
    }

    public int getLectionid() {
        return lectionid;
    }

    public void setLectionid(int lectionid) {
        this.lectionid = lectionid;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }
}
