package com.danilo.salaaula.view;

import com.danilo.salaaula.fachada.Fachada;
import com.danilo.salaaula.models.Professor;
import com.danilo.salaaula.models.Student;
import com.danilo.salaaula.models.User;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        int profOrStudent = professorOrStudentMenu();
        int signInSignUp = menuSignInSignUp();
        switch (profOrStudent) {
            case 1: {
                professorMenu(signInSignUp);
                break;
            }
            case 2: {
                studentMenu(signInSignUp);
            }
        }
    }

    public static void professorMenu(int signInSignUp) {
        switch (signInSignUp) {
            case 1: {
                Professor professor = singInProfessor();
                // TODO continuar fluxo
                break;
            }
            case 2: {
                signUpProfessor();
                break;
            }
        }
    }

    public static void studentMenu(int signInSignUp) {
        switch (signInSignUp) {
            case 1: {
                Student student = singInStudent();
                break;
            }
            case 2: {
                signUpStudent();
                break;
            }
            default:
                System.out.println("Wrong choice");
                break;
        }
    }

    public static Professor singInProfessor() {
        Fachada.inicializar();
        Scanner input = new Scanner(System.in);
        Professor ret = null;
        try {
            System.out.print("Emai: ");
            String email = input.nextLine();

            System.out.print("Password: ");
            String password = input.nextLine();

            Professor professor = Fachada.signInProfessor(email, password);
            ret =  professor;
        } catch(Exception e) {
            System.out.printf("Eror: %s\n", e.getMessage());
        }
        Fachada.finalizar();
        return ret;
    }

    public static Student singInStudent() {
        Fachada.inicializar();
        Scanner input = new Scanner(System.in);
        Student ret = null;
        try {
            System.out.print("Emai: ");
            String email = input.nextLine();

            System.out.print("Password: ");
            String password = input.nextLine();

            Student student = Fachada.signInStudent(email, password);
            ret = student;
        } catch(Exception e) {
            System.out.printf("Eror: %s\n", e.getMessage());
        }
        Fachada.finalizar();
        return ret;
    }

    public static void signUpStudent() {
        Fachada.inicializar();
        try {
            String data[] = readStudentProfessorData();
            Fachada.addStudent(data[0], data[1], data[2], data[3]);
            System.out.println("Sign up successfully");
        } catch(Exception e) {
            System.out.printf("Eror: %s\n", e.getMessage());
        }
        Fachada.finalizar();
    }


    public static void signUpProfessor() {
        Fachada.inicializar();
        try {
            String data[] = readStudentProfessorData();
            Fachada.addProfessor(data[0], data[1], data[2], data[3]);
            System.out.println("Sign up successfully");
        } catch(Exception e) {
            System.out.printf("Eror: %s\n", e.getMessage());
        }
        Fachada.finalizar();
    }

    public static String[] readStudentProfessorData() {
        Scanner input = new Scanner(System.in);

        System.out.print("CPF: ");
        String cpf = input.nextLine();

        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("Emai: ");
        String email = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        String arr[] = {cpf, name, email, password};
        return arr;
    }

    public static int menuSignInSignUp() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        System.out.println("1. Sign in");
        System.out.println("2. Sign up");
        System.out.print("Option: ");
        choice = input.nextInt();

        return choice;
    }

    public static int professorOrStudentMenu() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        System.out.println("1. Professor");
        System.out.println("2. Student");
        System.out.print("Option: ");
        choice = input.nextInt();

        return choice;
    }

    public static void addProfessor() {
        Fachada.inicializar();
        try {
//            Fachada.addProfessor("1209","Geohot", "geohot@gmail.com", "1234");
            Fachada.addProfessor("1","Messi", "messi@gmail.com", "1234");
            System.out.println("Professor adicionado");
        } catch (Exception e) {
            System.out.printf("Error. %s", e.getMessage());
        }
        Fachada.finalizar();
    }
}
