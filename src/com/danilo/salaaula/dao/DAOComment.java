package com.danilo.salaaula.dao;

import com.danilo.salaaula.models.Comment;
import com.db4o.query.Query;

import java.util.List;

public class DAOComment extends DAO<Comment> {
    @Override
    public Comment read(Object chave) {
        String comment = (String) chave;
        Query q = manager.query();
        q.descend("comment").constrain(comment);
        List<Comment> comments = q.execute();
        if (comments.size() > 0)
            return comments.get(0);

        return null;
    }
}
