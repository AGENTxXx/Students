package ru.innopolis.server.models;

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

    @Column(name = "groupNum")
    String groupNum;

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    //@Column(name = "lectionCount")

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getSex() {
        return sex;
    }

    public String getGroupNum() {
        return groupNum;
    }


    public EStudent(int id, String fio, String sex, String groupNum) {
        super();
        this.id = id;
        this.fio = fio;
        this.sex = sex;
        this.groupNum = groupNum;
    }

    public EStudent(String fio, String sex, String groupNum) {
        super();
        this.fio = fio;
        this.sex = sex;
        this.groupNum = groupNum;
    }



    public EStudent() {
        super();
    }
}
