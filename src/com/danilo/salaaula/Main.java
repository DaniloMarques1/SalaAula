package com.danilo.salaaula;

import com.danilo.salaaula.models.*;
import com.danilo.salaaula.models.Class;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //Criar um pouco do fluxo, verificar se esta funcionando...
        User u1      = new User("1", "Danilo", "danilo@gmail.com", "1234", UserType.STUDENT);
        Professor p1 = new Professor("2", "Messi", "messi@gmail.com", "12345");
        Class c1     = new Class("POB", p1);
        c1.addStudentToClass(u1);
        p1.createClass(c1);

        Professor p2 = new Professor("3", "Diniz", "diniz@gmail.com", "12345");
        Class c2     = new Class("Matematica", p2);
        c2.addStudentToClass(u1);
        p2.createClass(c2);

        // listando as turmas de um determinado aluno
        List<Class> turmas = u1.getClasses();
        for (Class c: turmas) {
            System.out.printf("Nome: %s\n", c.getName());
            System.out.printf("Professor: %s\n", c.getAuthor().getName());
        }
        System.out.println("");
        System.out.println("");

        List<Class> pTurmas = p1.getClasses();
        for (Class c: pTurmas) {
            System.out.printf("Nome: %s\n", c.getName());
        }

        Class pob = p1.getClasses().get(0);
        Post post = new Post(p1, "Post 1");
        pob.addPost(post);
    }
}
