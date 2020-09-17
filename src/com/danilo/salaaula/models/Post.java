package com.danilo.salaaula.models;

import java.util.List;

public class Post {
    private User author;
    private String title;
    private List<Comment> commentaries;

    public Post(User author, String title) {
        this.author = author;
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
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
        //TODO
    }
}