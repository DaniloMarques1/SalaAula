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
