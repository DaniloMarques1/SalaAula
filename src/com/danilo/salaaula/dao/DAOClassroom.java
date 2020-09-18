package com.danilo.salaaula.dao;

import com.danilo.salaaula.models.ClassRoom;
import com.db4o.query.Query;

import java.util.List;


public class DAOClassroom extends DAO<ClassRoom> {

    @Override
    public ClassRoom read(Object chave) {
        String name = (String) chave;
        Query q = manager.query();
        q.constrain(ClassRoom.class);
        q.descend("name").constrain(name);
        List<ClassRoom> classes = q.execute();
        if (classes.size() > 0) {
            return classes.get(0);
        }

        return null;
    }
}
