package com.danilo.salaaula.models;

public class Professor extends User {
    public Professor(String cpf, String name, String email, String password, UserType type) {
        super(cpf, name, email, password, type);
    }

    public void createClass(String className) {
        //TODO
    }
}
