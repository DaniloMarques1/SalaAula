package com.danilo.salaaula.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;

import com.danilo.salaaula.models.ClassName;
import com.danilo.salaaula.models.User;
import com.danilo.salaaula.models.Post;
import com.danilo.salaaula.models.Comment;
import com.danilo.salaaula.models.Professor;

public abstract class DAO<T> implements DAOInterface<T> {
    protected static ObjectContainer manager;

    public static void open(){
        if(manager==null){
            abrirBancoLocal();
            //abrirBancoServidor();
        }
    }
    public static void abrirBancoLocal(){
        //Backup.criar("banco.db4o");
        //new File("banco.db4o").delete();  //apagar o banco
        EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration();
        config.common().messageLevel(0);  // 0,1,2,3...
        config.common().objectClass(User.class).cascadeOnDelete(true);;
        config.common().objectClass(User.class).cascadeOnUpdate(true);;
        config.common().objectClass(User.class).cascadeOnActivate(true);


        config.common().objectClass(Professor.class).cascadeOnDelete(true);;
        config.common().objectClass(Professor.class).cascadeOnUpdate(true);;
        config.common().objectClass(Professor.class).cascadeOnActivate(true);


        config.common().objectClass(Comment.class).cascadeOnDelete(true);;
        config.common().objectClass(Comment.class).cascadeOnUpdate(true);;
        config.common().objectClass(Comment.class).cascadeOnActivate(true);

        config.common().objectClass(Post.class).cascadeOnDelete(true);;
        config.common().objectClass(Post.class).cascadeOnUpdate(true);;
        config.common().objectClass(Post.class).cascadeOnActivate(true);


        config.common().objectClass(ClassName.class).cascadeOnDelete(true);;
        config.common().objectClass(ClassName.class).cascadeOnUpdate(true);;
        config.common().objectClass(ClassName.class).cascadeOnActivate(true);


        // 		indexacao de atributos
        config.common().objectClass(User.class).objectField("cpf").indexed(true);
        config.common().objectClass(Post.class).objectField("title").indexed(true);
        config.common().objectClass(Professor.class).objectField("cpf").indexed(true);
        config.common().objectClass(ClassName.class).objectField("name").indexed(true);

        manager = 	Db4oEmbedded.openFile(config, "banco.db4o");
    }

    /*
    public static void abrirBancoServidor(){
        ClientConfiguration config = Db4oClientServer.newClientConfiguration( ) ;
        config.common().messageLevel(0);   //0,1,2,3,4
        config.common().objectClass(Livro.class).cascadeOnDelete(false);;
        config.common().objectClass(Livro.class).cascadeOnUpdate(true);;
        config.common().objectClass(Livro.class).cascadeOnActivate(true);

        config.common().objectClass(Autor.class).cascadeOnDelete(false);;
        config.common().objectClass(Autor.class).cascadeOnUpdate(true);;
        config.common().objectClass(Autor.class).cascadeOnActivate(true);
        // 		indexacao de atributos
        config.common().objectClass(Livro.class).objectField("titulo").indexed(true);
        config.common().objectClass(Autor.class).objectField("nome").indexed(true);

        manager = Db4oClientServer.openClient(config,"127.0.0.1",8080,"usuario1","senha1");
    }
     */

    public static void close(){
        if(manager!=null) {
            manager.close();
            manager=null;
        }
    }

    //----------CRUD-----------------------

    public void create(T obj){
        manager.store( obj );
    }

    public abstract T read(Object chave);

    public T update(T obj){
        manager.store(obj);
        return obj;
    }

    public void delete(T obj) {
        manager.delete(obj);
    }

    public void refresh(T obj){
        manager.ext().refresh(obj, Integer.MAX_VALUE);
    }

    @SuppressWarnings("unchecked")
    public List<T> readAll(){
        // pegando o tipo a partir da "classe" T
        Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        Query q = manager.query();
        q.constrain(type); // todos os objetivos desse tipo
        return (List<T>) q.execute();
    }

    //--------transacao---------------
    public static void begin(){
    }		// tem que ser vazio

    public static void commit(){
        manager.commit();
    }
    public static void rollback(){
        manager.rollback();
    }



}
