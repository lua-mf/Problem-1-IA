package puzzle.app;

import puzzle.core.Board;
import puzzle.core.BestFirst;
import puzzle.core.Ilayout;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Classe principal que roda o algoritmo Best-First
 * para resolver o 8-puzzle.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a configuração inicial (ex: 023145678): ");
        String initial = sc.next();

        System.out.println("Digite a configuração objetivo (ex: 123405678): ");
        String goal = sc.next();

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
}
