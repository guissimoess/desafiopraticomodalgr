package com.guilherme;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int valorEmprestimo;

        System.out.println("---------------------------------");
        System.out.println("PROGRAMA DE EMPRÉSTIMO DA MODALGR");
        System.out.println("---------------------------------");
        System.out.print("Nome do colaborador: ");
        String nomeColaborador = sc.nextLine();
        System.out.print("Data de admissão: ");
        String dataAdmissaoStr = sc.next();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAdmissao = LocalDate.parse(dataAdmissaoStr, format);
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataAdmissao, dataAtual);

        System.out.print("Salário atual: R$ ");
        int salarioAtual = sc.nextInt();

        do {
            System.out.print("Valor do empréstimo: R$ ");
            valorEmprestimo = sc.nextInt();
            if ((valorEmprestimo % 2 != 0) || (valorEmprestimo > 2 * salarioAtual)) {
                System.out.println("Insira um valor válido!");
            }
        } while ((valorEmprestimo % 2 != 0) || (valorEmprestimo > 2 * salarioAtual));


        // tentei refatorar essas 4 variaveis mas nao consegui pensar em nada

        int valorEmprestimo1 = valorEmprestimo;
        int valorEmprestimo2 = valorEmprestimo;
        int primeiraMetade = valorEmprestimo/2;
        int segundaMetade = valorEmprestimo/2;

        if (periodo.getYears() >= 5) {
            System.out.println("Opções de retirada: ");
            System.out.print("1 - Notas de maior valor: ");

            int [] notas = {100, 50, 20, 10, 5, 2};
            int [] quantidadeNotasMaiores = new int[notas.length];
            int [] quantidadeNotasMenores = new int[notas.length];
            int [] quantidadeNotas2 = new int[notas.length];
            int [] quantidadeNotas3 = new int[notas.length];

            for (int i=0; i<notas.length; i++) {
                int nota = notas[i];
                while (valorEmprestimo1 >= nota) {
                    valorEmprestimo1 -= nota;
                    quantidadeNotasMaiores[i]++;
                }
            }
            for (int i=0; i < notas.length; i++) {
                if (quantidadeNotasMaiores[i] > 0) {
                    System.out.print(quantidadeNotasMaiores[i] + " x " + notas[i] + " reais. ");
                }
            }
            System.out.println();


            System.out.print("2 - Notas de menor valor: ");

            for (int i=2; i<notas.length; i++) {
                int nota = notas[i];
                while (valorEmprestimo2 >= nota) {
                    valorEmprestimo2 -= nota;
                    quantidadeNotasMenores[i]++;
                }
            }
            for (int i=2; i < notas.length; i++) {
                if (quantidadeNotasMenores[i] > 0) {
                    System.out.print(quantidadeNotasMenores[i] + " x " + notas[i] + " reais. ");
                }
            }
            System.out.println();

            System.out.print("3 - Notas meio a meio: ");
            System.out.print(primeiraMetade + " reais em nota de maior valor: ");


            for (int i=0; i<notas.length; i++) {
                int nota = notas[i];
                while (primeiraMetade >= nota) {
                    primeiraMetade -= nota;
                    quantidadeNotas2[i]++;
                }
            }
            for (int i=0; i < notas.length; i++) {
                if (quantidadeNotas2[i] > 0) {
                    System.out.print(quantidadeNotas2[i] + " x " + notas[i] + " reais. ");
                }
            }

            System.out.println();
            System.out.print("                      " +segundaMetade + " reais em nota de menor valor: ");

            for (int i=2; i<notas.length; i++) {
                int nota = notas[i];
                while (segundaMetade >= nota) {
                    segundaMetade -= nota;
                    quantidadeNotas3[i]++;
                }
            }

            for (int i=2; i < notas.length; i++) {
                if (quantidadeNotas3[i] > 0) {
                    System.out.print(quantidadeNotas3[i] + " x " + notas[i] + " reais. ");
                }
            }
            System.out.println();

            System.out.print("Digite sua opção de retirada: ");
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> System.out.print(nomeColaborador + ", você escolheu a opção de notas maiores.");
                case 2 -> System.out.print(nomeColaborador + ", você escolheu a opção de notas menores.");
                case 3 -> System.out.print(nomeColaborador + ", você escolheu a opção de notas meio a meio.");
                default -> System.out.print(nomeColaborador + ", você digitou uma opção inválida. Refaça novamente.");
            }
        } else {
            System.out.println("Agradecemos seu interesse, mas você não atende os requisitos mínimos do programa.");
        }
    }
}