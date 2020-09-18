package com.danilo.salaaula.models;

import java.util.ArrayList;
import java.util.List;

// sala de aula/TURMA
public class ClassRoom {
    private String name;
    private Professor author;
    private List<Post> posts;
    private List<User> students; // apenas alunos tipo STUDENT

    public ClassRoom(String name, Professor author) {
        this.name = name;
        this.author = author;
        this.students = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Professor getAuthor() {
        return author;
    }

    public void setAuthor(Professor author) {
        this.author = author;
    }

    public List<User> getStudents() {
        return this.students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public void addStudentToClass(User student) {
        //TODO: isso ta bom?
        this.students.add(student);
        student.getClasses().add(this);
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

}
