package com.danilo.salaaula.dao;

import com.danilo.salaaula.models.Professor;
import com.db4o.query.Query;

import java.util.List;

public class DAOProfessor extends DAO<Professor> {
    @Override
    public Professor read(Object chave) {
        String cpf = (String) chave;
        Query q = manager.query();
        q.constrain(Professor.class);
        q.descend("cpf").constrain(cpf);
        List<Professor> users = q.execute();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}
