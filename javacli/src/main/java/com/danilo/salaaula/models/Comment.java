package com.danilo.salaaula.models;

public class Comment {
    private User author;
    private String comment;

    public Comment(User author, String comment) {
        this.author = author;
        this.comment = comment;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
