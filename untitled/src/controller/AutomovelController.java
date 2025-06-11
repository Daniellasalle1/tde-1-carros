package controller;

import model.Automovel;

import java.io.*;
import java.util.*;

public class AutomovelController {
    private List<Automovel> automoveis;
    private final String arquivo = "automoveis.txt";

    public AutomovelController() {
        automoveis = new ArrayList<>();
        carregarDados();
    }

    public void inserirAutomovel(Automovel a) {
        if (buscarPorPlaca(a.getPlaca()) == null) {
            automoveis.add(a);
            System.out.println("Automóvel adicionado com sucesso.");
        } else {
            System.out.println("Placa já cadastrada!");
        }
    }

    public void removerAutomovel(String placa) {
        Automovel a = buscarPorPlaca(placa);
        if (a != null) {
            automoveis.remove(a);
            System.out.println("Automóvel removido.");
        } else {
            System.out.println("Automóvel não encontrado.");
        }
    }

    public void alterarAutomovel(String placa, String modelo, String marca, int ano, double valor) {
        Automovel a = buscarPorPlaca(placa);
        if (a != null) {
            a.setModelo(modelo);
            a.setMarca(marca);
            a.setAno(ano);
            a.setValor(valor);
            System.out.println("Automóvel alterado.");
        } else {
            System.out.println("Automóvel não encontrado.");
        }
    }

    public Automovel buscarPorPlaca(String placa) {
        for (Automovel a : automoveis) {
            if (a.getPlaca().equalsIgnoreCase(placa)) {
                return a;
            }
        }
        return null;
    }

    public void listarOrdenado(String criterio) {
        Comparator<Automovel> comparator;
        switch (criterio.toLowerCase()) {
            case "modelo":
                comparator = Comparator.comparing(Automovel::getModelo);
                break;
            case "marca":
                comparator = Comparator.comparing(Automovel::getMarca);
                break;
            case "placa":
            default:
                comparator = Comparator.comparing(Automovel::getPlaca);
        }
        automoveis.stream()
                .sorted(comparator)
                .forEach(a -> System.out.println(a.toFormattedString()));
    }

    public void salvarDados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (Automovel a : automoveis) {
                bw.write(a.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private void carregarDados() {
        File file = new File(arquivo);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 5) {
                    String placa = partes[0];
                    String modelo = partes[1];
                    String marca = partes[2];
                    int ano = Integer.parseInt(partes[3]);
                    double valor = Double.parseDouble(partes[4]);
                    automoveis.add(new Automovel(placa, modelo, marca, ano, valor));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}