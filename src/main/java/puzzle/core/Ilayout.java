package puzzle.core;

import java.util.List;

/**
 * Interface que representa o contrato para qualquer problema
 * que possa ser resolvido pelo algoritmo Best-First.
 */
public interface Ilayout {

    // Função de como gerar sucessores
    List<Ilayout> children();

    // Função para verificar se chegou ao objetivo
    boolean isGoal(Ilayout l);

    // Função para saber qual o custo de transição
    double getK();
}
