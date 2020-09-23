package com.danilo.salaaula.view;

import com.danilo.salaaula.fachada.Fachada;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        System.out.println("1. Sign in");
        System.out.println("2. Sign up");
        System.out.print("Option: ");
        choice = input.nextInt();
        System.out.printf("Chouce= %d\n", choice);
    }

    public static void singIn() {
        //TODO
    }

    public static void singUp() {
        //TODO
    }

    public static void addProfessor() {
        Fachada.inicializar();
        try {
//            Fachada.addProfessor("1209","Geohot", "geohot@gmail.com", "1234");
            Fachada.addProfessor("1","Messi", "messi@gmail.com", "1234");
            System.out.println("Professor adicionado");
        } catch (Exception e) {
            System.out.printf("Error. %s", e.getMessage());
        }
        Fachada.finalizar();
    }
}
