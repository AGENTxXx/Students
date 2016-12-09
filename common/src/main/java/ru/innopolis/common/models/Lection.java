package ru.innopolis.common.models;

import java.io.Serializable;

/**
 * Created by Alexander Chuvashov on 24.11.2016.
 */

public class Lection implements Serializable {
    int id;

    String date;

    String theme;

    public int getLectStudId() {
        return lectStudId;
    }

    public void setLectStudId(int lectStudId) {
        this.lectStudId = lectStudId;
    }

    int lectStudId;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTheme() {
        return theme;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Lection(int id, String date, String theme) {
        super();
        this.id = id;
        this.date = date;
        this.theme = theme;
    }

    public Lection(int lectStudId, int id, String date, String theme) {
        super();
        this.lectStudId = lectStudId;
        this.id = id;
        this.date = date;
        this.theme = theme;
    }

    public Lection(String date, String theme) {
        super();
        this.date = date;
        this.theme = theme;
    }

    public Lection( ) {
        super();
    }

}
