package com.danilo.salaaula.models;

// sala de aula/TURMA
public class Class {
    private String name;
    private Professor author;
//    private Posts[] posts; // TODO
    private User[] alunos; // apenas alunos tipo STUDENT

    public Class(String name, Professor author) {
        this.name = name;
        this.author = author;
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

    public User[] getAlunos() {
        return alunos;
    }

    public void setAlunos(User[] alunos) {
        this.alunos = alunos;
    }

    // APENAS user do tipo STUDENT
    public void addStudentToClass(User student) {
        //TODO
    }

//    public void addPost(Post post) {
//        //TODO
//    }
}
