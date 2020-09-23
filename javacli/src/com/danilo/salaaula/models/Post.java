package com.danilo.salaaula.models;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private Professor author;
    private String title;
    private List<Comment> commentaries;

    public Post(Professor author, String title) {
        this.author = author;
        this.title = title;
        this.commentaries = new ArrayList<>();
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(Professor author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Comment> getCommentaries() {
        return this.commentaries;
    }

    public void addComment(Comment commnet) {
        this.commentaries.add(commnet);
    }
}
