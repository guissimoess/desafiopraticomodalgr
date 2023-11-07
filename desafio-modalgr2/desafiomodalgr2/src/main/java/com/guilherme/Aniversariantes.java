package com.guilherme;

/* A ModalGR possui um arquivo texto contendo o nome completo, e-mail e data de nascimento de seus consultores,
separados por pipeline (|):
O time do RH deseja que todo início de cada mês seja gerado um novo arquivo texto com apenas os dados dos
aniversariantes do mês corrente, para assim, enviarem uma mensagem personalizada de felicitações.
Você foi escolhido para criar uma solução que atenda esses requisitos.
*/

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Aniversariantes {
    public static void main(String[] args) {

        // os caminho para os arquivo de texto devem ser trocados
        String listaConsultores = "C:\\Users\\pichau\\Documents\\desafio-modalgr2\\desafiomodalgr2\\lista_consultores.txt";
        String listaAniversariantes = "C:\\Users\\pichau\\Documents\\desafio-modalgr2\\desafiomodalgr2\\aniversariantesDoMes.txt";

        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String mesAtual = sdf.format(new Date());

        try (BufferedReader leitor = new BufferedReader(new FileReader(listaConsultores));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(listaAniversariantes))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split("\\|");
                String dataNascimentoConsultor = dados[2];
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date dataNascimento = dateFormat.parse(dataNascimentoConsultor);
                String mesAniversario = sdf.format(dataNascimento);

                if (mesAniversario.equals(mesAtual)) {
                    escritor.write(dados[0] + "|" + dados[1] + "|" + dataNascimentoConsultor);
                    escritor.newLine();
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}