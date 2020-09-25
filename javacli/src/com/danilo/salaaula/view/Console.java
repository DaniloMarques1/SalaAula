package com.danilo.salaaula.view;

import com.danilo.salaaula.fachada.Fachada;
import com.danilo.salaaula.models.*;

import java.util.List;
import java.util.Scanner;

public class Console {
    static private final Scanner input = new Scanner(System.in);
    static private User currentLoginUser;

    static private User getCurrentLoginuser() {
        return currentLoginUser;
    }
    static private void setCurrentLoginUser(User user) {
        currentLoginUser = user;
    }

    public static void main(String[] args) {
        int profOrStudent = professorOrStudentMenu();
        while (true) {
            int signInSignUp = menuSignInSignUp();
            switch (profOrStudent) {
                case 1: {
                    professorMenu(signInSignUp);
                    break;
                }
                case 2: {
                    studentMenu(signInSignUp);
                    break;
                }
                default:
                    return;
            }
        }
    }

    private static void professorMenu(int signInSignUp) {
         switch (signInSignUp) {
             case 1: {
                 Professor professor = singInProfessor();
                 if (professor == null)
                     break;
                 setCurrentLoginUser(professor);
                 fluxoProfessor(professor);
                 break;
             }
             case 2: {
                 signUpProfessor();
                 break;
             }
             default:
                 return;
         }
    }

    private static void studentMenu(int signInSignUp) {
         switch (signInSignUp) {
             case 1: {
                 Student student = singInStudent();
                 if (student == null)
                     break;;
                 setCurrentLoginUser(student);
                 fluxoStudent(student);
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
            System.out.println("Cadastrado com sucesso!");
        } catch(Exception e) {
            System.out.printf("Erro ao cadastrar: %s\n", e.getMessage());
        }
        Fachada.finalizar();
    }


    public static void signUpProfessor() {
        Fachada.inicializar();
        try {
            String data[] = readStudentProfessorData();
            Fachada.addProfessor(data[0], data[1], data[2], data[3]);
            System.out.println("Cadastrado com sucesso!");
        } catch(Exception e) {
            System.out.printf("Erro ao cadastrar: %s\n", e.getMessage());
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
        while (true) {
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
                default:
                    return;
            }
        }
    }

    private static void fluxoStudent(Student student) {
        while (true) {
            int choice = showMenuStudent();
            switch (choice) {
                case 1: {
                    listClasses(student);
                    break;
                }
                case 2: {
                    System.out.print("Nome da disciplina: ");
                    String className = input.nextLine();
                    flowClassRoomStudent(className);
                    break;
                }
                default:
                    return;
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
        input.nextLine();

        return choice;
    }

    private static int showMenuStudent() {
        int choice = 0;
        System.out.println("1. Listar minhas disciplinas");
        System.out.println("2. Acessar disciplina");
        System.out.print("Opcao,: ");
        choice = input.nextInt();
        input.nextLine();

        return choice;
    }

    private static void listClasses(Professor professor) {
        Fachada.inicializar();
        List<ClassRoom> classes = Fachada.getAllProfessorsClasses(professor.getEmail());

        if (classes.isEmpty())
            System.out.println("Voce ainda nao possui turmas cadastradas");

        for (ClassRoom c: classes) {
            System.out.println("======================");
            System.out.printf("Nome da turma: %s\n", c.getName());
            System.out.printf("Quantidade de estudantes: %d\n", c.getStudents().size());
            System.out.println("=====================");
            System.out.println("");
        }

        Fachada.finalizar();
    }

    private static void listClasses(Student student) {
        Fachada.inicializar();
        List<ClassRoom> classes = Fachada.getStudentsClasses(student.getEmail());

        if (classes.isEmpty())
            System.out.println("Voce ainda nao possui turmas");

        for (ClassRoom c: classes) {
            System.out.println("======================");
            System.out.printf("Nome da turma: %s\n", c.getName());
            System.out.printf("Quantidade de estudantes: %d\n", c.getStudents().size());
            System.out.println("=====================");
            System.out.println("");
        }

        Fachada.finalizar();
    }

    private static void addClassRoom(Professor professor) {
        Fachada.inicializar();
        try {
            System.out.print("Nome da turma: ");
            String className = input.nextLine();
            Fachada.addClassRoom(className, professor.getEmail());
        } catch(Exception e) {
            //TODO
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
           System.out.println("");
       }

       Fachada.finalizar();
    }

    private static void listAllClasses() {
        Fachada.inicializar();
        List<ClassRoom> classes = Fachada.getAllClasses();
        for (ClassRoom c: classes) {
            System.out.println("=====================");
            System.out.printf("Nome da turma: %s\n", c.getName());
            System.out.printf("Nome do professor da turma: %s\n", c.getAuthor().getName());
            System.out.printf("Email do professor da turma: %s\n", c.getAuthor().getEmail());
            System.out.println("=====================");
            System.out.println("");
        }
        Fachada.finalizar();
    }

    private static void flowClassRoomProfessor(String className) {
        Fachada.inicializar();
        try {
            ClassRoom c = Fachada.getClassRoomByname(className);
            while (true) {
                int choice = showMenuClassRoom();
                switch (choice) {
                    case 1: {
                        listClassStudents(c.getName());
                        break;
                    }
                    case 2: {
                        listStudentsNotInClass(c.getName());
                        break;
                    }
                    case 3: {
                        addStudentToClass(c.getName());
                        break;
                    }
                    case 4: {
                        addPostToClassRoom(c.getName(), c.getAuthor().getEmail());
                        break;
                    }
                    case 5: {
                        listClassRoomPosts(c);
                        break;
                    }
                    case 6: {
                        System.out.print("Titulo do post: ");
                        String postTitle = input.nextLine();
                        flowPost(postTitle);
                        break;
                    }
                    case 7: {
                        changeClassRoomName(c.getName());
                        break;
                    }
                    default:
                        return;
                }
            }
        } catch(Exception e) {
            //TODO
        }
        Fachada.finalizar();
    }

    private static void flowClassRoomStudent(String className) {
        Fachada.inicializar();
        try {
            ClassRoom c = Fachada.getClassRoomByname(className);
            while (true) {
                int choice = showMenuClassRoomStudent();
                switch (choice) {
                    case 1: {
                        listClassStudents(className);
                        break;
                    }
                    case 2: {
                        listClassRoomPosts(c);
                        break;
                    }
                    case 3: {
                        System.out.print("Titulo do post: ");
                        String postTitle = input.nextLine();
                        flowPost(postTitle);
                        break;
                    }
                    default:
                        return;
                }
            }
        } catch(Exception e) {
            //TODO
        }
        Fachada.finalizar();
    }

    private static int showMenuClassRoom() {
        int choice = 1;
        System.out.println("1. Listar alunos da turma");
        System.out.println("2. Listar alunos que nao estao na turma");
        System.out.println("3. Adicionar aluno");
        System.out.println("4. Adicionar posts");
        System.out.println("5. Listar posts");
        System.out.println("6. Acessar post");
        System.out.println("7. Trocar nome da turma");
        System.out.print("Opcao,: ");
        choice = input.nextInt();
        input.nextLine();

        return choice;
    }

    private static int showMenuClassRoomStudent() {
        int choice = 1;
        System.out.println("1. Listar alunos da turma");
        System.out.println("2. Listar posts");
        System.out.println("3. Acessar post");
        System.out.print("Opcao,: ");
        choice = input.nextInt();
        input.nextLine();

        return choice;
    }

    private static void listClassStudents(String className) {
        Fachada.inicializar();
        try {
            List<Student> students = Fachada.listStudentsInClass(className);
            for (Student student: students) {
                System.out.println("=====================");
                System.out.printf("CPF do aluno: %s\n", student.getCpf());
                System.out.printf("Email do aluno: %s\n", student.getEmail());
                System.out.printf("Nome do aluno: %s\n", student.getName());
                System.out.println("=====================");
                System.out.println("");
            }

        } catch (Exception e) {
            //TODO
        }
        Fachada.finalizar();
    }


    private static void listStudentsNotInClass(String className) {
        Fachada.inicializar();
        try {
            List<Student> students = Fachada.listUsersNotInClass(className);
            for (Student student: students) {
                System.out.printf("Alunos que nao estao nao disciplna de %s\n", className);
                System.out.println("=====================");
                System.out.printf("CPF do aluno: %s\n", student.getCpf());
                System.out.printf("Nome do aluno: %s\n", student.getName());
                System.out.println("=====================");
                System.out.println("");
            }
        } catch(Exception e) {
            //TODO
        }
        Fachada.finalizar();
    }

    private static void addStudentToClass(String className) {
        Fachada.inicializar();

        try {
            System.out.print("email do aluno: ");
            String email = input.nextLine();
            Fachada.addStudentToClass(email, className);
            System.out.println("Aluno adicionado com sucesso!");
        } catch (Exception e) {
           //TODO
        }

        Fachada.finalizar();
    }

    private static void addPostToClassRoom(String className, String profEmail) {
        Fachada.inicializar();

        try {
            System.out.print("Titulo do post: ");
            String postTitle = input.nextLine();
            Fachada.addPostToClassRoom(profEmail, className, postTitle);
            System.out.println("Post adicionado com sucesso!");
        } catch (Exception e) {
            //TODO
        }

        Fachada.finalizar();
    }

    private static void listClassRoomPosts(ClassRoom c) {
        Fachada.inicializar();
        List<Post> posts = Fachada.getClassRoomPosts(c.getName());
        for (Post post: posts) {
            System.out.println("=====================");
            System.out.printf("Titulo do post: %s\n", post.getTitle());
            System.out.printf("Quantidade de comentarios: %d\n", post.getCommentaries().size());
            System.out.println("=====================");
            System.out.println("");
        }
        Fachada.finalizar();
    }

    private static void flowPost(String postTitle) {
        Fachada.inicializar();
        try {
            Post post = Fachada.getPostByTitle(postTitle);
            while (true) {
                int choice = menuPost();
                switch (choice) {
                    case 1: {
                        listPostComments(post.getTitle());
                        break;
                    }
                    case 2: {
                        addPostComments(post.getTitle());
                        break;
                    }
                    default:
                        return;
                }
            }
        } catch (Exception e) {
            //TODO
        }
        Fachada.finalizar();
    }

    private static void changeClassRoomName(String oldName) {
        Fachada.inicializar();
        try {
            System.out.printf("Nome atual: %s\n", oldName);
            System.out.print("Novo nome da turma: ");
            String newName = input.nextLine();
            Fachada.updateClassRoomName(oldName, newName);
        } catch (Exception e) {
            //TODO
        }
        Fachada.finalizar();
    }

    private static int menuPost() {
       int choice = 0;
       System.out.println("1. Listar comentarios");
       System.out.println("2. Adicionar comentario");
       System.out.print("Opcao,: ");
       choice = input.nextInt();
       input.nextLine();

       return choice;
    }

    private static void listPostComments(String title) {
        Fachada.inicializar();
        try {
            List<Comment> comments = Fachada.getPostCommentaries(title);
            for (Comment comment: comments) {
                System.out.println("=====================");
                System.out.printf("Comentario: %s\n", comment.getComment());
                System.out.printf("Autor do comentario: %s\n", comment.getAuthor().getName());
                System.out.println("=====================");
                System.out.println("");
            }
        } catch (Exception e) {
            //TODO
        }
        Fachada.finalizar();
    }

    private static void addPostComments(String title) {
        Fachada.inicializar();
        try {
            System.out.print("Comentario: ");
            String comment = input.nextLine();
            Fachada.addCommentToPost(title, comment, getCurrentLoginuser());
            System.out.println("Comentario adicionado com sucesso");
        } catch (Exception e) {
            //TODO
        }
        Fachada.finalizar();

    }
}
