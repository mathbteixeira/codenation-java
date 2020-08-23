package br.com.codenation.desafioexe;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = DesafioApplication.fibonacci();
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
