package com.danilo.salaaula.models;


public class User {
    private String cpf; // chave primaria
    private String name;
    private String email;
    private String password;
    private UserType type;

    public User(String cpf, String name, String email,
                String password, UserType type) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
//    private Turmas[] turmas; // TODO
}
