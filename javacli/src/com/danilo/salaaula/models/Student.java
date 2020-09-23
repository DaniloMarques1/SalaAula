package com.danilo.salaaula.models;

public class Student extends User {
    public Student(String cpf, String name, String email, String password) {
        super(cpf, name, email, password, UserType.STUDENT);
    }

    public void addClass(ClassRoom c) {
        this.getClasses().add(c);
    }
}
