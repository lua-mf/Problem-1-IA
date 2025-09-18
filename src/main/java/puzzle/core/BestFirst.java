package puzzle.core;

import java.util.*;

/**
 * Implementação do algoritmo Best-First Search.
 */
public class BestFirst {
    protected Queue<State> abertos;        // estados ainda não testados
    private Map<Ilayout, State> fechados; // estados já testados
    private State actual;                  // estado atual
    private Ilayout objective;             // estado objetivo

    /**
     * Classe interna que representa um estado do problema.
     */
    public static class State {
        private Ilayout layout;
        private State father;
        private double g; // custo acumulado

        public State(Ilayout l, State n) {
            layout = l;
            father = n;
            if (father != null) {
                g = father.g + l.getK();
            } else {
                g = 0.0; // estado inicial
            }
        }

        public String toString() {
            return layout.toString();
        }

        public double getG() {
            return g;
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;
            State n = (State) o;
            return this.layout.equals(n.layout);
        }
    }

    // Gera os sucessores de um estado
    final private List<State> sucessores(State n) {
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children();
        for (Ilayout e : children) {
            if (n.father == null || !e.equals(n.father.layout)) {
                State nn = new State(e, n);
                sucs.add(nn);
            }
        }
        return sucs;
    }

    // Executa o algoritmo Best-First
    final public Iterator<State> solve(Ilayout s, Ilayout goal) {
        objective = goal;

        // Fila de prioridade (ordena pelo custo acumulado g)
        abertos = new PriorityQueue<>(10,
                (s1, s2) -> (int) Math.signum(s1.getG() - s2.getG()));
        fechados = new HashMap<>();

        // Insere o estado inicial
        abertos.add(new State(s, null));

        while (!abertos.isEmpty()) {
            // Pega o estado de menor custo
            actual = abertos.poll();

            // Se atingiu o objetivo → reconstrói caminho
            if (actual.layout.isGoal(objective)) {
                return buildPath(actual);
            }

            // Senão, expande
            fechados.put(actual.layout, actual);
            List<State> sucs = sucessores(actual);

            for (State st : sucs) {
                if (!fechados.containsKey(st.layout) && !abertos.contains(st)) {
                    abertos.add(st);
                }
            }
        }

        // Se não encontrou solução
        return null;
    }

    // Reconstrói o caminho da solução do estado inicial até o objetivo
    private Iterator<State> buildPath(State goal) {
        List<State> path = new ArrayList<>();
        for (State n = goal; n != null; n = n.father) {
            path.add(n);
        }
        Collections.reverse(path);
        return path.iterator();
    }
}
