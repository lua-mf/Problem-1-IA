package puzzle.app;

import puzzle.core.Board;
import puzzle.core.BestFirst;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashSet;

/**
 * Classe principal que roda o algoritmo Best-First
 * para resolver o 8-puzzle.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String initial = "";
        String goal = "";

        // Loop para garantir uma entrada inicial válida
        while (true) {
            System.out.println("Digite a configuração inicial (ex: 023145678): ");
            initial = sc.next();
            try {
                validateBoardString(initial);
                break; // Sai do loop se a entrada for válida
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        // Loop para garantir uma entrada objetivo válida
        while (true) {
            System.out.println("Digite a configuração objetivo (ex: 123405678): ");
            goal = sc.next();
            try {
                validateBoardString(goal);
                break; // Sai do loop se a entrada for válida
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        BestFirst solver = new BestFirst();
        Iterator<BestFirst.State> it = solver.solve(new Board(initial), new Board(goal));

        if (it == null) {
            System.out.println("Nenhuma solução encontrada.");
        } else {
            System.out.println("\nSolução encontrada:\n");
            BestFirst.State last = null;
            while (it.hasNext()) {
                last = it.next();
                System.out.println(last); // imprime o tabuleiro
            }
            if (last != null) {
                System.out.println("Custo total: " + last.getG());
            }
        }

        sc.close();
    }

    /**
     * Valida se a string de entrada para o tabuleiro é válida.
     */
    private static void validateBoardString(String s) {
        if (s.length() != 9) {
            throw new IllegalArgumentException("A entrada deve ter exatamente 9 dígitos.");
        }

        HashSet<Character> digits = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("A entrada deve conter apenas dígitos.");
            }
            if (c < '0' || c > '8') {
                throw new IllegalArgumentException("A entrada deve conter apenas dígitos de 0 a 8.");
            }
            if (!digits.add(c)) {
                throw new IllegalArgumentException("A entrada não pode ter dígitos repetidos.");
            }
        }
    }
}