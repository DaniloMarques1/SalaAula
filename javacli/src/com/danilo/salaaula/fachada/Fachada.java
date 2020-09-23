package com.danilo.salaaula.fachada;

import com.danilo.salaaula.dao.*;

import com.danilo.salaaula.models.*;

import java.util.List;

public class Fachada {
    private static DAOStudent daoStudent     = new DAOStudent();
    private static DAOProfessor daoProfessor = new DAOProfessor();
    private static DAOClassroom daoClassroom = new DAOClassroom();
    private static DAOPost daoPost           = new DAOPost();
    private static DAOComment daoComment     = new DAOComment();

    public static void inicializar() {
        DAO.open();
    }

    public static void finalizar() {
        DAO.close();
    }

    public static void addStudent(String cpf, String name, String email, String password) throws Exception {
        DAO.begin();
        Student studentExist = daoStudent.read(email, cpf);
        if (studentExist != null) {
            throw new Exception("User already registered");
        }
        Student student = new Student(cpf, name, email, password);
        daoStudent.create(student);
        DAO.commit();
    }

    public static void addProfessor(String cpf, String name, String email, String password) throws Exception {
        DAO.begin();
        Professor professorExist = daoProfessor.read(email, cpf);
        if (professorExist != null) {
            throw new Exception("Professor already registered");
        }
        Professor professor = new Professor(cpf, name, email, password);
        daoProfessor.create(professor);
        DAO.commit();
    }

    public static void addClassRoom(String name, String cpfProfessor) throws  Exception {
        DAO.begin();

        ClassRoom classRoomExist = daoClassroom.read(name);
        if (classRoomExist != null) {
            throw new Exception("Class already exist");
        }
        Professor professor = daoProfessor.read(cpfProfessor); // verifica se o professor existe no banco
        if (professor == null) {
            throw new Exception("Professor it is not registered");
        }
        ClassRoom c = new ClassRoom(name, professor);
        professor.createClass(c);
        daoClassroom.create(c);
        daoProfessor.update(professor);

        DAO.commit();
    }

    public static void addStudentToClass(String userCpf, String className) throws Exception {
        DAO.begin();

        ClassRoom c = daoClassroom.read(className);
        if (c == null) {
            throw new Exception("Class does not exist");
        }

        Student student = daoStudent.read(userCpf);
        if (student == null) {
            throw new Exception("Student not registered");
        }

        c.addStudentToClass(student);
        student.addClass(c);
        daoClassroom.update(c);
        daoStudent.update(student);

        DAO.commit();
    }

    public static List<Student> listUsersNotInClass(String className) throws Exception {
        ClassRoom c = daoClassroom.read(className);

        if (c == null)
            throw new Exception("Class does not exist");


        List<Student> students = daoStudent.readAll(c.getName());
        return students;
    }

    public static void addPostToClassRoom(String profCpf, String className, String postTile) throws Exception {
        DAO.begin();

        ClassRoom c = daoClassroom.read(className);
        if (c == null)
            throw new Exception("Class does not exist");

        Professor professor = daoProfessor.read(profCpf);
        if (c == null)
            throw new Exception("Professor not registered");


        Post post = new Post(professor, postTile);
        c.addPost(post);
        daoClassroom.update(c);
        daoPost.create(post);

        DAO.commit();
    }

    // alunos/professores podem adicionar comentarios
    public static void addCommentToPost(String postTitle, String comment, User user) throws Exception{
        DAO.begin();

        Post post = daoPost.read(postTitle);
        if (post == null)
            throw new Exception("Post not found");

        Comment c = new Comment(user, comment);
        post.addComment(c);
        daoPost.update(post);
        daoComment.create(c);

        DAO.commit();
    }

    public static Student signInStudent(String email, String password) throws Exception {
        Student student = daoStudent.read(email, password);
        if (student == null)
            throw new Exception("Email/password incorret");

        return student;
    }

    public static Professor signInProfessor(String email, String password) throws Exception {
        Professor professor = daoProfessor.read(email, password);
        if (professor == null)
            throw new Exception("Email/password incorret");

        return professor;
    }

    public static List<Professor> getAllProfessors() {
        return daoProfessor.readAll();
    }
}
