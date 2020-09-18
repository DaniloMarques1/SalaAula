package com.danilo.salaaula.dao;

import com.danilo.salaaula.models.ClassRoom;
import com.danilo.salaaula.models.User;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import java.util.List;

public class DAOUser extends DAO<User> {
    @Override
    public User read(Object chave) {
        String cpf = (String) chave;
        Query q = manager.query();
        q.constrain(User.class);
        q.descend("cpf").constrain(cpf);
        List<User> users = q.execute();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public List<User> readAll(String className) {
        Query q = manager.query();
        q.constrain(User.class);
        q.constrain(new UserNotInClass(className));
        List<User> users = q.execute();

        return users;
    }
}

class UserNotInClass implements Evaluation {
    private String className;
    public UserNotInClass(String className) {
        this.className = className;
    }

    @Override
    public void evaluate(Candidate candidate) {
        User user = (User) candidate.getObject();
        boolean shouldInclude = true;
        for (ClassRoom c: user.getClasses()) {
            if (c.getName() == this.className) {
                shouldInclude = false;
            }
        }
        candidate.include(shouldInclude);
    }
}
