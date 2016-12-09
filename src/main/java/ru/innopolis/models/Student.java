package ru.innopolis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Alexander Chuvashov on 24.11.2016.
 */
public class Student {

    int id;

    String fio;

    String sex;

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

    public Student(int id, String fio, String sex, String group) {
        this.fio = fio;
        this.sex = sex;
        this.group = group;
        this.id = id;
    }
}
