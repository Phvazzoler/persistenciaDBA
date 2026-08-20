package org.example;

import org.example.Contato;
import org.example.ContatoDAO;
import org.example.DataBase;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ContatoDAO dao = new ContatoDAO();
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        DataBase.criarTabaela();
        System.out.println("Agenta de contato ");
        int opcao = 0 ;
        while (opcao!=5){
            exibirMenu();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        adicionarContato();
                        break;
                    case 2:
                        listarContato();
                        break;
                    case 3:
                        atualizarContato();
                        break;
                    case 4:
                        removerContato();
                        break;
                    case 5:
                        System.out.println("ENcerrando...");
                        break;
                    default:
                        System.out.println("Opçao invalida..");
                        break;
                }
            }catch (InputMismatchException e ) {
                System.out.println("Erro Por favor , digite um numero ." +e.getMessage());
                scanner.nextLine();
            }
        }
    }
    private static void exibirMenu () {
        System.out.println("\n ----Agenda----");
        System.out.println("1- Adicionar Contato ");
        System.out.println("2- Listar Contato ");
        System.out.println("3- Atualizar Contato  ");
        System.out.println("4- Remover Contato ");
        System.out.println("5- Sair");
        System.out.println("Escolha uma opçao ");
    }
    private static void adicionarContato(){
        System.out.println("\n Adicionar Contato");
        System.out.println("Nome : ");
        String nome = scanner.nextLine();
        System.out.println("Telefone : ");
        String telefone = scanner.nextLine();
        dao.adicionar(new Contato(nome , telefone));
        System.out.println("contato salvo com sucesso !!");
    }
    private static void listarContato(){
        System.out.println("\n ---Lista de Contatos--- ");
        List<Contato> contatos = dao.listar();
        if (contatos.isEmpty()){
            System.out.println("Nenhum contato cadastrado ");
        }else {
            contatos.forEach(System.out::println);

        }
    }
    private static void atualizarContato(){
        System.out.println("\n----Atualizar Contato ----");
        System.out.println("----Digitte o ID do contato a ser atualizado :");
        try {
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Novo nome : ");
            String nome = scanner.nextLine();
            System.out.println("Novo Telefone ");
            String telefone = scanner.nextLine();
            dao.atualizar(new Contato(id, nome, telefone));
            System.out.println("Contato atualizdo com sucesso ");
        }catch (InputMismatchException e ){
            System.out.println("ID Invalido.Digite um Numero ");
            scanner.nextLine();
        }
    }
    private static void removerContato (){
        System.out.println("\n----Remover Contato ----");
        System.out.println("Digite o ID de contato a ser removido ");
        try {
            int id = scanner.nextInt();
            scanner.nextLine();
            dao.remover(id);
            System.out.println("Contato removido com sucesso ");
        }catch (InputMismatchException e ){
            System.out.println("ID invalido . Digite um numero ");
            scanner.nextLine();
        }
    }

}