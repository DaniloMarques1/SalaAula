package com.danilo.salaaula.view;

import com.danilo.salaaula.fachada.Fachada;
import com.danilo.salaaula.models.ClassRoom;
import com.danilo.salaaula.models.Professor;
import com.danilo.salaaula.models.Student;

import java.util.List;
import java.util.Scanner;

public class Console {
    static private final Scanner input = new Scanner(System.in);

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

    private static void professorMenu(int signInSignUp) {
        switch (signInSignUp) {
            case 1: {
                Professor professor = singInProfessor();
                fluxoProfessor(professor);
                break;
            }
            case 2: {
                signUpProfessor();
                break;
            }
        }
    }

    private static void studentMenu(int signInSignUp) {
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
                System.out.println("Escolha incorreta");
                break;
        }
    }

    private static Professor singInProfessor() {
        Fachada.inicializar();
        Professor ret = null;
        try {
            System.out.print("Emai: ");
            String email = input.nextLine();

            System.out.print("Senha: ");
            String password = input.nextLine();

            Professor professor = Fachada.signInProfessor(email, password);
            ret =  professor;
        } catch(Exception e) {
            System.out.printf("Eror: %s\n", e.getMessage());
        }
        Fachada.finalizar();
        return ret;
    }

    private static Student singInStudent() {
        Fachada.inicializar();
        Student ret = null;
        try {
            System.out.print("Emai: ");
            String email = input.nextLine();

            System.out.print("Senha: ");
            String password = input.nextLine();

            Student student = Fachada.signInStudent(email, password);
            ret = student;
        } catch(Exception e) {
            System.out.printf("Erro: %s\n", e.getMessage());
        }
        Fachada.finalizar();
        return ret;
    }

    private static void signUpStudent() {
        Fachada.inicializar();
        try {
            String data[] = readStudentProfessorData();
            Fachada.addStudent(data[0], data[1], data[2], data[3]);
            System.out.println("Entrou com sucesso!");
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
            System.out.println("Entrou com sucesso");
        } catch(Exception e) {
            System.out.printf("Erro: %s\n", e.getMessage());
        }
        Fachada.finalizar();
    }

    private static String[] readStudentProfessorData() {
        System.out.print("CPF: ");
        String cpf = input.nextLine();

        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("Emai: ");
        String email = input.nextLine();

        System.out.print("Senha: ");
        String password = input.nextLine();

        String arr[] = {cpf, name, email, password};
        return arr;
    }

    private static int menuSignInSignUp() {
        int choice = 0;
        System.out.println("1. Entrar");
        System.out.println("2. Cadastrar");
        System.out.print("Option: ");
        choice = input.nextInt();
        input.nextLine();

        return choice;
    }

    private static int professorOrStudentMenu() {
        int choice = 0;
        System.out.println("1. Professor");
        System.out.println("2. Aluno");
        System.out.print("Opcao: ");
        choice = input.nextInt();
        input.nextLine();

        return choice;
    }

    private static void fluxoProfessor(Professor professor) {
        int choice = showMenuProfessor();
        switch (choice) {
            case 1: {
                listClasses(professor);
                break;
            }
            case 2: {
                listStudents();
                break;
            }
            case 3: {
                addClassRoom(professor);
                break;
            }
            case 4: {
                System.out.print("Nome da disciplina: ");
                String className = input.nextLine();
                flowClassRoomProfessor(className);
                break;
            }
        }
    }

    private static int showMenuProfessor() {
        int choice = 0;
        System.out.println("1. Listar disciplinas");
        System.out.println("2. Listar alunos");
        System.out.println("3. Criar disciplina");
        System.out.println("4. Acessar disciplina");
        System.out.print("Opcao,: ");
        choice = input.nextInt();

        return choice;
    }

    private static void listClasses(Professor professor) {
        if (professor.getClasses().size() == 0)
            System.out.println("Voce ainda nao possui turmas cadastradas");

        for (ClassRoom c: professor.getClasses()) {
            System.out.println("=====================");
            System.out.printf("Nome da turma: %s\n", c.getName());
            System.out.printf("Quantidade de estudantes: %d\n", c.getStudents().size());
            System.out.println("=====================");
        }
    }

    private static void addClassRoom(Professor professor) {
        Fachada.inicializar();
        try {
            System.out.print("Nome da turma: ");
            String className = input.nextLine();
            Fachada.addClassRoom(className, professor.getCpf());
        } catch(Exception e) {

        }
        Fachada.finalizar();
    }

    private static void listStudents() {
       Fachada.inicializar();

       List<Student> students = Fachada.listStudentes();
       for (Student student: students) {
           System.out.println("=====================");
           System.out.printf("Cpf do aluno: %s\n", student.getCpf());
           System.out.printf("Nome do aluno: %s\n", student.getName());
           System.out.println("=====================");
       }

       Fachada.finalizar();
    }

    private static void flowClassRoomProfessor(String className) {
        Fachada.inicializar();
        try {
            ClassRoom c = Fachada.getClassRoomByname(className);
        } catch(Exception e) {
            //TODO
        }
        Fachada.finalizar();
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
