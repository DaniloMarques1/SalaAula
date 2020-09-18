package com.danilo.salaaula.models;

public class Professor extends User {
    public Professor(String cpf, String name, String email, String password) {
        super(cpf, name, email, password, UserType.PROFESSOR);
    }

    public void createClass(ClassRoom c) {
        //TODO: verificar se isso ta legal
        this.getClasses().add(c);
    }

    public String toString() {
        String s = String.format("Professor: %s", this.getName());
        return s;
    }

}
