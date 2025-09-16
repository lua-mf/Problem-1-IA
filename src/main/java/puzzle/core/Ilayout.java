package puzzle.core;

import java.util.List;

// Interface que define o contrato para qualquer "estado" do puzzle
public interface Ilayout {
    // Gera os filhos (novos estados possíveis a partir do atual)
    List<Ilayout> children();

    // Verifica se este estado é igual ao objetivo
    boolean isGoal(Ilayout l);

    // Retorna o custo de mover do estado atual para o próximo
    double getK();
}
