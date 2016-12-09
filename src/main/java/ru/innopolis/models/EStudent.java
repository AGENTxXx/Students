package ru.innopolis.models;

import javax.persistence.*;

/**
 * Created by Alexander Chuvashov on 01.12.2016.
 */
@Entity
@Table(name = "students")
public class EStudent {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="id_students_seq",
            sequenceName="id_students_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="id_students_seq")
    int id;

    @Column(name = "fio")
    String fio;

    @Column(name = "sex")
    String sex;

    @Column(name = "group")
    String group;

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getSex() {
        return sex;
    }

    public String getGroup() {
        return group;
    }

    public EStudent(int id, String fio, String sex, String group) {
        this.fio = fio;
        this.sex = sex;
        this.group = group;
        this.id = id;
    }
}
