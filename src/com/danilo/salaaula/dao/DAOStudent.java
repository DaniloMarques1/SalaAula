package com.danilo.salaaula.dao;

import com.danilo.salaaula.models.ClassRoom;
import com.danilo.salaaula.models.Student;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import java.util.List;

public class DAOStudent extends DAO<Student> {
    @Override
    public Student read(Object chave) {
        String cpf = (String) chave;
        Query q = manager.query();
        q.constrain(Student.class);
        q.descend("cpf").constrain(cpf);
        List<Student> users = q.execute();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public Student read(String email, String cpf) {
        Query q = manager.query();
        q.constrain(Student.class);
        q.constrain(new StudentRegistered(email, cpf));
        List<Student> students = q.execute();

        if (students.size() > 0)
            return students.get(0);

        return null;
    }

    public List<Student> readAll(String className) {
        Query q = manager.query();
        q.constrain(Student.class);
        q.constrain(new StudentNotInClass(className));
        List<Student> students = q.execute();

        return students;
    }
}

class StudentNotInClass implements Evaluation {
    private String className;
    public StudentNotInClass(String className) {
        this.className = className;
    }

    @Override
    public void evaluate(Candidate candidate) {
        Student user = (Student) candidate.getObject();
        boolean shouldInclude = true;
        for (ClassRoom c: user.getClasses()) {
            if (c.getName() == this.className) {
                shouldInclude = false;
            }
        }
        candidate.include(shouldInclude);
    }
}

class StudentRegistered implements Evaluation {
    private String email;
    private String cpf;

    public StudentRegistered(String email, String cpf) {
        this.email = email;
        this.cpf   = cpf;
    }

    @Override
    public void evaluate(Candidate candidate) {
        Student std = (Student) candidate.getObject();
        boolean flag = false;
        if (std.getCpf().equals(this.cpf) || std.getEmail().equals(this.email))
            flag = true;
        candidate.include(flag);
    }
}
