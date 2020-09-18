package com.danilo.salaaula;

import com.danilo.salaaula.fachada.Fachada;
import com.danilo.salaaula.models.*;
import com.danilo.salaaula.models.ClassRoom;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        addProfessor();
//        addStudents();
//        studentsNotClass();
//        createObjects();
//        System.out.printf("");
//        addStudents();
//        System.out.printf("");
//        listAll();
//        System.out.printf("");
//        addStudentToStragerClass();
//        System.out.printf("");
//        listAll();
//        System.out.printf("");
//        addStudents();
    }

    public static void createObjectsNoDatabase() {
        //Criar um pouco do fluxo, verificar se esta funcionando...
        User u1      = new Student("1", "Danilo", "danilo@gmail.com", "1234");
        Professor p1 = new Professor("2", "Messi", "messi@gmail.com", "12345");
        ClassRoom c1     = new ClassRoom("POB", p1);
        c1.addStudentToClass(u1);
        p1.createClass(c1);

        Professor p2 = new Professor("3", "Diniz", "diniz@gmail.com", "12345");
        ClassRoom c2     = new ClassRoom("Matematica", p2);
        c2.addStudentToClass(u1);
        p2.createClass(c2);

        // listando as turmas de um determinado aluno
        List<ClassRoom> turmas = u1.getClasses();
        for (ClassRoom c: turmas) {
            System.out.printf("Nome: %s\n", c.getName());
            System.out.printf("Professor: %s\n", c.getAuthor().getName());
        }
        System.out.println("");
        System.out.println("");

        List<ClassRoom> pTurmas = p1.getClasses();
        for (ClassRoom c: pTurmas) {
            System.out.printf("Nome: %s\n", c.getName());
        }

        ClassRoom pob = p1.getClasses().get(0);
        Post post = new Post(p1, "Post 1");
        pob.addPost(post);
    }

    public static void addProfessor() {
        Fachada.inicializar();
        try {
//            Fachada.addProfessor("1209","Geohot", "geohot@gmail.com", "1234");
            Fachada.addProfessor("1209","Geohot", "geo@gmail.com", "1234");
            System.out.println("Professor adicionado");
        } catch (Exception e) {
            System.out.printf("Error. %s", e.getMessage());
        }
        Fachada.finalizar();
    }

    public static void createObjects() {
        Fachada.inicializar();

        try {
            // criando uma nova turma

            Fachada.addProfessor("1", "Strager", "strager@gmail.com", "1234");
            Fachada.addClassRoom("Matematics 1", "1");

            System.out.println("Turma criada com sucesso!");
        } catch (Exception e) {
            System.out.printf("Error. %s", e.getMessage());
        }

        Fachada.finalizar();
    }

    public static void addStudents() {
        Fachada.inicializar();

        try{
//            Fachada.addStudent("2", "Danilo", "danilo@gmail.com", "1234");
//            Fachada.addStudent("34", "Messi", "messi@gmail.com", "1234");
            Fachada.addStudent("50", "Leo", "leo@gmail.com", "1234");
            System.out.println("Estudante criado com sucesso");
        } catch (Exception e) {

            System.out.printf("Error. %s", e.getMessage());
        }

        Fachada.finalizar();
    }

    public static void listAll() {
        Fachada.inicializar();
        try {
            List<Professor> ps = Fachada.getAllProfessors();
            for (Professor p: ps) {
                System.out.println(p);
                System.out.println("Turmas do professor:");
                System.out.println("========================");
                for (ClassRoom c: p.getClasses()) {
                    System.out.println(c);
                    System.out.println("Alunos que nao estao na turma:");
                    System.out.println("========================");
                    List<Student> students = Fachada.listUsersNotInClass(c.getName());
                    for (Student u: students) {
                        System.out.println(u);
                    }
                }
            }
        } catch (Exception e) {
            System.out.printf("Error. %s", e.getMessage());
        }

        Fachada.finalizar();
    }

    public static void addStudentToStragerClass() {
        Fachada.inicializar();
        try {
            Fachada.addStudentToClass("34", "Matematics 1");
        } catch(Exception e) {
            System.out.printf("Error. %s", e.getMessage());
        }
        Fachada.finalizar();
    }

    public static void studentsNotClass() {
        Fachada.inicializar();
        try {
            List<Student> students = Fachada.listUsersNotInClass( "Matematics 1");
            for (Student std: students) {
                System.out.println(std);
            }
        } catch(Exception e) {
            System.out.printf("Error. %s", e.getMessage());
        }
        Fachada.finalizar();
    }
}
