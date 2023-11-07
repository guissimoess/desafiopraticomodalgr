package com.guilherme;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.KeySpec;
import java.util.Base64;

/*
    A ModalGR possui um cofre eletrônico que precisa estar protegido por 3 senhas, sendo
    essas criptografadas e armazenadas numa base de dados SQL. Cada uma dessas senhas deve
    possuir uma regra/método de criptografia distinta, mas ambas devem utilizar uma única
    chave secreta. Para isso, você foi escolhido para criar o sistema de criptografia dessas senhas.
    Chave secreta:
    #modalGR#GPTW#top#maiorEmpresaTecnologia#baixadaSantista

 */
public class Main {

    public static final String chaveSecreta = "#modalGR#GPTW#top#maiorEmpresaTecnologia#baixadaSantista";

    public static SecretKeySpec geraChave (String chaveSecreta) throws Exception{
        byte [] chaveBytes = chaveSecreta.getBytes();
        SecretKeyFactory fac = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(chaveSecreta.toCharArray(), chaveBytes, 65536, 256);
        SecretKey chaveCriptografada = new SecretKeySpec(fac.generateSecret(spec).getEncoded(), "AES");
        return (SecretKeySpec) chaveCriptografada;
    }

    public static String criptografarAESECB (String senha) throws Exception {
        SecretKey chaveGerada = geraChave(chaveSecreta);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, chaveGerada);
        byte [] cifraTexto = cipher.doFinal(senha.getBytes());
        return Base64.getEncoder().encodeToString(cifraTexto);
    }

    public static String criptografarAESCBC (String senha) throws Exception {
        SecretKey chaveGerada = geraChave(chaveSecreta);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, chaveGerada);
        byte [] cifraTexto = cipher.doFinal(senha.getBytes());
        return Base64.getEncoder().encodeToString(cifraTexto);
    }

    public static String criptografarAESCFB (String senha) throws Exception {
        SecretKey chaveGerada = geraChave(chaveSecreta);
        Cipher cipher = Cipher.getInstance("AES/CFB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, chaveGerada);
        byte [] cifraTexto = cipher.doFinal(senha.getBytes());
        return Base64.getEncoder().encodeToString(cifraTexto);
    }

    public static void main(String[] args) throws Exception {

        String senha1 = "#MODALGRMELHOREMPRESA";
        String senha2 = "#FORMACAO2024";
        String senha3 = "#DESAFIOCRIPTOGRAFIA";


        System.out.println("Senha " + senha1 + " criptografada: " +criptografarAESECB(senha1));
        System.out.println("Senha " + senha2 + " criptografada: " +criptografarAESCBC(senha2));
        System.out.println("Senha " + senha3 + " criptografada: " +criptografarAESCFB(senha3));

    }
}