package com.tokagestudio.todo.data.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Todo extends RealmObject {

    @PrimaryKey
    private String id;
    private String title;
    private String note;
    private Date timestamp;

    public Todo() {
    }

    public Todo(String id, String title, String note, Date timestamp) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
