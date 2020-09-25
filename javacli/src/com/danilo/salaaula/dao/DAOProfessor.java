package com.danilo.salaaula.dao;

import com.danilo.salaaula.models.Professor;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import java.util.List;

public class DAOProfessor extends DAO<Professor> {
    @Override
    public Professor read(Object chave) {
        String email = (String) chave;
        Query q = manager.query();
        q.constrain(Professor.class);
        q.descend("email").constrain(email);
        List<Professor> users = q.execute();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public Professor read(String email, String cpf) {
        Query q = manager.query();
        q.constrain(Professor.class);
        q.constrain(new ProfessorRegistered(email, cpf));
        List<Professor> users = q.execute();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}


class ProfessorRegistered implements Evaluation {
    private String email;
    private String cpf;

    public ProfessorRegistered(String email, String cpf) {
        this.email = email;
        this.cpf   = cpf;
    }

    @Override
    public void evaluate(Candidate candidate) {
        Professor prof = (Professor) candidate.getObject();
        boolean flag = false;
        if (prof.getCpf().equals(this.cpf) || prof.getEmail().equals(this.email))
            flag = true;
        candidate.include(flag);
    }
}
