package com.danilo.salaaula.models;

public class Professor extends User {
    public Professor(String cpf, String name, String email, String password) {
        super(cpf, name, email, password, UserType.PROFESSOR);
    }

    public void createClass(ClassName c) {
        //TODO: verificar se isso ta legal
        this.getClasses().add(c);
    }

}
