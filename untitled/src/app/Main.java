package app;

import controller.AutomovelController;
import model.Automovel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AutomovelController controller = new AutomovelController();
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU DE OPÇÕES =====");
            System.out.println("1 - Incluir automóvel");
            System.out.println("2 - Remover automóvel");
            System.out.println("3 - Alterar dados de automóvel");
            System.out.println("4 - Consultar automóvel por placa");
            System.out.println("5 - Listar automóveis (ordenado)");
            System.out.println("6 - Salvar e sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> {
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Ano: ");
                    int ano = Integer.parseInt(sc.nextLine());
                    System.out.print("Valor: ");
                    double valor = Double.parseDouble(sc.nextLine());
                    controller.inserirAutomovel(new Automovel(placa, modelo, marca, ano, valor));
                }
                case 2 -> {
                    System.out.print("Placa do automóvel a remover: ");
                    controller.removerAutomovel(sc.nextLine());
                }
                case 3 -> {
                    System.out.print("Placa do automóvel a alterar: ");
                    String placa = sc.nextLine();
                    System.out.print("Novo modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Nova marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Novo ano: ");
                    int ano = Integer.parseInt(sc.nextLine());
                    System.out.print("Novo valor: ");
                    double valor = Double.parseDouble(sc.nextLine());
                    controller.alterarAutomovel(placa, modelo, marca, ano, valor);
                }
                case 4 -> {
                    System.out.print("Placa do automóvel a consultar: ");
                    var a = controller.buscarPorPlaca(sc.nextLine());
                    System.out.println((a != null) ? a.toFormattedString() : "Automóvel não encontrado.");
                }
                case 5 -> {
                    System.out.print("Ordenar por (placa/modelo/marca): ");
                    controller.listarOrdenado(sc.nextLine());
                }
                case 6 -> controller.salvarDados();
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 6);

        System.out.println("Sistema encerrado.");
    }
}
