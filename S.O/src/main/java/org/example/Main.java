package org.example;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite uma sequência de números inteiros separados por espaço:");

        String[] entradaUsuario = scanner.nextLine().split(" ");
        int[] paginas = new int[entradaUsuario.length];
        for (int i = 0; i < entradaUsuario.length; i++) {
            paginas[i] = Integer.parseInt(entradaUsuario[i]);
        }

        System.out.println("Digite o número de páginas (frames):");
        int numeroDeFrames = scanner.nextInt();

        PoliticaSubstituicaoPaginas algoritmos = new PoliticaSubstituicaoPaginas(paginas, numeroDeFrames);
        System.out.println(algoritmos);

        JFrame janelaGrafico = new JFrame("Visualização de Page Faults");
        janelaGrafico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaGrafico.setSize(500, 300);
        janelaGrafico.add(new GraficoBarras(algoritmos));
        janelaGrafico.setVisible(true);
    }
}
