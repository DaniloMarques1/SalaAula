package com.danilo.salaaula.fachada;

import com.danilo.salaaula.dao.DAO;
import com.danilo.salaaula.dao.DAOStudent;
import com.danilo.salaaula.dao.DAOProfessor;
import com.danilo.salaaula.dao.DAOClassroom;

import com.danilo.salaaula.dao.DAOStudent;
import com.danilo.salaaula.models.*;

import java.io.DataOutputStream;
import java.util.List;

public class Fachada {
    private static DAOStudent daoStudent = new DAOStudent();
    private static DAOProfessor daoProfessor = new DAOProfessor();
    private static DAOClassroom daoClassroom = new DAOClassroom();

    public static void inicializar() {
        DAO.open();
    }

    public static void finalizar() {
        DAO.close();
    }

    public static void addStudent(String cpf, String name, String email, String password) throws Exception {
        DAO.begin();
        Student studentExist = daoStudent.read(cpf);
        if (studentExist != null) {
            throw new Exception("User already registered");
        }
        Student student = new Student(cpf, name, email, password);
        daoStudent.create(student);
        DAO.commit();
    }

    public static void addProfessor(String cpf, String name, String email, String password) throws Exception {
        DAO.begin();
        Professor professorExist = daoProfessor.read(cpf);
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

    public static List<Student> listUsersNotInClass(ClassRoom c) {
        List<Student> users = daoStudent.readAll(c.getName());
        return users;
    }

    public static List<Professor> getAllProfessors() {
        return daoProfessor.readAll();
    }
}
