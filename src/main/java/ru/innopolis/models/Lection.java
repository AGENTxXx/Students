package ru.innopolis.models;

import javax.persistence.*;

/**
 * Created by Alexander Chuvashov on 24.11.2016.
 */

public class Lection {
    int id;

    String date;

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

    public Lection(int id, String date, String theme) {
        super();
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
