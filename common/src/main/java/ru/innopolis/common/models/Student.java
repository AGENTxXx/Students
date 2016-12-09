package ru.innopolis.common.models;

import java.io.Serializable;

/**
 * Created by Alexander Chuvashov on 24.11.2016.
 */
public class Student implements Serializable {

    int id;

    String fio;

    String sex;

    String group;

    long lectionCount;

    public long getLectionCount() {
        return lectionCount;
    }

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

    public Student(int id, String fio, String sex, String group) {
        this.fio = fio;
        this.sex = sex;
        this.group = group;
        this.id = id;
    }

    public Student(int id, String fio, String sex, String group, long lectionCount) {
        this.fio = fio;
        this.sex = sex;
        this.group = group;
        this.id = id;
        this.lectionCount = lectionCount;
    }
}
