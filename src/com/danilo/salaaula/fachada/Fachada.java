package com.danilo.salaaula.fachada;

import com.danilo.salaaula.dao.DAO;
import com.danilo.salaaula.dao.DAOUser;
import com.danilo.salaaula.dao.DAOProfessor;
import com.danilo.salaaula.dao.DAOClassroom;

import com.danilo.salaaula.models.ClassRoom;
import com.danilo.salaaula.models.Professor;
import com.danilo.salaaula.models.User;
import com.danilo.salaaula.models.UserType;

import java.util.List;

public class Fachada {
    private static DAOUser daoUser = new DAOUser();
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
        User userExist = daoUser.read(cpf);
        if (userExist != null) {
            throw new Exception("User already registered");
        }
        User user = new User(cpf, name, email, password, UserType.STUDENT);
        daoUser.create(user);
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
        User student = daoUser.read(userCpf);
        c.addStudentToClass(student);
        student.addClass(c);
        daoClassroom.update(c);
        daoUser.update(student);

        DAO.commit();
    }

    public static List<User> listUsersNotInClass(ClassRoom c) {
        List<User> users = daoUser.readAll(c.getName());
        return users;
    }

    public static List<Professor> getAllProfessors() {
        return daoProfessor.readAll();
    }
}
