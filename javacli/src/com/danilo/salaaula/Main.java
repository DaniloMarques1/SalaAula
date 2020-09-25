package com.danilo.salaaula;

import com.danilo.salaaula.fachada.Fachada;
import com.danilo.salaaula.models.*;
import com.danilo.salaaula.models.ClassRoom;
import com.danilo.salaaula.view.Console;

import java.util.List;

public class Main {

    public static void main(String[] args) {
//        Console.main(args);
//        cadastrarProfessores();
//        cadastrarAlunos();
//        listarAlunos();
//        cadastrarTurma();
//        adicionarAlunoATurma();
//        listarAlunosDaTurma();
//        listarAlunosQueNaoEstaoNaTurma();
//        cadastrarPostParaTurma();
//        listarPostaDaTurma();
//        adicionarComentarioParaPost();
//        listarComentariosDoPost();
//        atualizarNomeDaTurma();
//        listarTurmas();
    }

    public static void cadastrarProfessores() {
        Fachada.inicializar();
        try {
            Fachada.addProfessor("1", "Fausto", "fausto@gmail.com", "1234");
            Fachada.addProfessor("2", "Luiz Carlos", "luiz@gmail.com", "1234");
            System.out.println("Professor cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.printf("Erro cadastrando professor: %s\n", e.getMessage());
        }
        Fachada.finalizar();
    }

    public static void cadastrarAlunos() {
        Fachada.inicializar();
        try {
            //Fachada.addStudent("3", "Danilo", "danilo@gmail.com", "1234");
            //Fachada.addStudent("4", "Fitz", "fitz@gmail.com", "1234");
            Fachada.addStudent("5", "Jose", "jose@gmail.com", "1234");
            System.out.println("Aluno cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.printf("Erro cadastrando aluno(s): %s\n", e.getMessage());
        }
        Fachada.finalizar();
    }

    public static void listarAlunos() {
        Fachada.inicializar();
        List<Student> students = Fachada.listStudentes();

        if (students.isEmpty()) {
            System.out.println("Nenhum estudante cadastrado");
            return;
        }
        listagemDeAlunos(students);
        Fachada.finalizar();
    }

    public static void cadastrarTurma() {
        Fachada.inicializar();
        try {
            Fachada.addClassRoom("Programacao orientedada a objetos", "luiz@gmail.com");
            System.out.println("Turma cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar turma");
        }
        Fachada.finalizar();
    }

    public static void listarTurmas() {
        Fachada.inicializar();
        List<ClassRoom> classes = Fachada.getAllClasses();
        for (ClassRoom c: classes) {
            System.out.printf("Nome da turma: %s\n", c.getName());
            System.out.printf("Quantidade de alunos: %d\n", c.getStudents().size());
            System.out.printf("Professor: %s\n", c.getAuthor().getName());
            System.out.println("======================");
            System.out.println("");
        }
        Fachada.finalizar();
    }

    public static void adicionarAlunoATurma() {
        Fachada.inicializar();
        try {
            Fachada.addStudentToClass("danilo@gmail.com", "Turma 1");
            Fachada.addStudentToClass("fitz@gmail.com", "Turma 1");
            System.out.println("Aluno(s) adicionado a turma com sucesso");
        } catch (Exception e) {
            System.out.printf("Erro ao adicionar aluno a turma: %s\n", e.getMessage());
        }
        Fachada.finalizar();
    }

    public static void listarAlunosDaTurma() {
        Fachada.inicializar();
        try {
            List<Student> students = Fachada.listStudentsInClass("Turma 1");
            listagemDeAlunos(students);
        } catch (Exception e) {
            System.out.printf("Erro ao listar os alunos da turma: %s\n", e.getMessage());
        }
        Fachada.finalizar();
    }

    private static void listarAlunosQueNaoEstaoNaTurma() {
        Fachada.inicializar();
        try {
            List<Student> students = Fachada.listUsersNotInClass("Turma 1");
            listagemDeAlunos(students);
        } catch (Exception e) {
            System.out.printf("Erro ao listar os alunos da turma: %s\n", e.getMessage());
        }
        Fachada.finalizar();
    }

    private static void cadastrarPostParaTurma() {
        Fachada.inicializar();
        try {
            Fachada.addPostToClassRoom("fausto@gmail.com", "Turma 1", "Post 2");
            System.out.println("Post adicionado com sucesso");
        } catch (Exception e) {
            System.out.printf("Erro ao adicionar post a turma: %s\n", e.getMessage());
        }
        Fachada.finalizar();
    }

    public static void listarPostaDaTurma() {
        Fachada.inicializar();

        List<Post> posts = Fachada.getClassRoomPosts("Turma 1");
        for (Post post: posts) {
            System.out.printf("Titulo do post: %s\n", post.getTitle());
            System.out.printf("Quantidade de comentarios: %s\n", post.getCommentaries().size());
            System.out.println("======================");
            System.out.println("");
        }

        Fachada.finalizar();
    }

    public static void adicionarComentarioParaPost() {
        Fachada.inicializar();
        try {
            User u1 = Fachada.signInProfessor("fausto@gmail.com", "1234");
            User u2 = Fachada.signInStudent("danilo@gmail.com", "1234");
            Fachada.addCommentToPost("Post 1", "Comentario 1", u1);
            Fachada.addCommentToPost("Post 1", "Comentario 2", u2);
            System.out.println("Comentario adicionado com sucesso");
        } catch (Exception e) {
            System.out.printf("Erro ao adicionar comentario ao post: %s\n", e.getMessage());
        }
        Fachada.finalizar();

    }

    public static void listarComentariosDoPost() {
        Fachada.inicializar();
        try {
            List<Comment> comments = Fachada.getPostCommentaries("Post 1");
            for (Comment comment: comments) {
                System.out.printf("Comentario: %s\n", comment.getComment());
                System.out.printf("Autor: %s\n", comment.getAuthor().getName());
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.printf("erro ao listar os comentarios %s\n", e.getMessage());
        }
        Fachada.finalizar();
    }

    public static void atualizarNomeDaTurma() {
        Fachada.inicializar();
        try {
            Fachada.updateClassRoomName("Turma 1", "Persistencia de Objetos");
            System.out.println("Nome alterado com sucesso");
        } catch (Exception e) {
            System.out.printf("Erro ao tentar atualizar o nome da turma: ", e.getMessage());
        }
        Fachada.finalizar();
    }

    private static void listagemDeAlunos(List<Student> students) {
        for (Student student: students){
            System.out.printf("Nome: %s\n", student.getName());
            System.out.printf("Email: %s\n", student.getEmail());
            System.out.printf("Cpf: %s\n", student.getCpf());
            System.out.println("======================");
            System.out.println("");
        }
    }

}
