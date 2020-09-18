package com.danilo.salaaula;

import com.danilo.salaaula.models.*;
import com.danilo.salaaula.models.ClassName;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //Criar um pouco do fluxo, verificar se esta funcionando...
        User u1      = new User("1", "Danilo", "danilo@gmail.com", "1234", UserType.STUDENT);
        Professor p1 = new Professor("2", "Messi", "messi@gmail.com", "12345");
        ClassName c1     = new ClassName("POB", p1);
        c1.addStudentToClass(u1);
        p1.createClass(c1);

        Professor p2 = new Professor("3", "Diniz", "diniz@gmail.com", "12345");
        ClassName c2     = new ClassName("Matematica", p2);
        c2.addStudentToClass(u1);
        p2.createClass(c2);

        // listando as turmas de um determinado aluno
        List<ClassName> turmas = u1.getClasses();
        for (ClassName c: turmas) {
            System.out.printf("Nome: %s\n", c.getName());
            System.out.printf("Professor: %s\n", c.getAuthor().getName());
        }
        System.out.println("");
        System.out.println("");

        List<ClassName> pTurmas = p1.getClasses();
        for (ClassName c: pTurmas) {
            System.out.printf("Nome: %s\n", c.getName());
        }

        ClassName pob = p1.getClasses().get(0);
        Post post = new Post(p1, "Post 1");
        pob.addPost(post);
    }
}
