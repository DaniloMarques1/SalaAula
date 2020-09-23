package com.danilo.salaaula.dao;

import com.danilo.salaaula.models.Post;
import com.db4o.query.Query;

import java.util.List;

public class DAOPost extends DAO<Post> {
    @Override
    public Post read(Object chave) {
        String title = (String) chave;
        Query q = manager.query();
        q.descend("title").constrain(title);
        List<Post> posts = q.execute();

        if (posts.size() > 0)
            return posts.get(0);

        return null;
    }
}
