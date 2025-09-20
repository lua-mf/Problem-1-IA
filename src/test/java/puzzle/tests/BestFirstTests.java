package puzzle.tests;

import org.junit.jupiter.api.Test;
import puzzle.core.BestFirst;
import puzzle.core.Board;
import puzzle.core.Ilayout;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes para validar o algoritmo Best-First Search no 8-puzzle.
 */
public class BestFirstTests {

    @Test
    public void testSolveSimplePuzzle() {
        Ilayout initial = new Board("023145678");
        Ilayout goal = new Board("123405678");

        BestFirst solver = new BestFirst();
        Iterator<BestFirst.State> solution = solver.solve(initial, goal);

        assertNotNull(solution, "Solução não deveria ser nula");

        BestFirst.State last = null;
        int steps = 0;
        while (solution.hasNext()) {
            last = solution.next();
            steps++;
        }

        assertNotNull(last, "O último estado não deveria ser nulo");
        assertEquals(goal, new Board(last.toString().replace("\n", "").replace(" ", "0")),
                "O estado final deve ser igual ao objetivo");
        assertEquals(2.0, last.getG(), "O custo total deve ser 2.0");
        assertEquals(3, steps, "Deve haver 3 estados (inicial, intermediário e objetivo)");
    }

    @Test
    public void testSolveMediumPuzzle() {
        Ilayout initial = new Board("123456780");
        Ilayout goal = new Board("436718520");

        BestFirst solver = new BestFirst();
        Iterator<BestFirst.State> solution = solver.solve(initial, goal);

        assertNotNull(solution, "A solução não deve ser nula.");

        int steps = 0;
        BestFirst.State last = null;
        while (solution.hasNext()) {
            last = solution.next();
            steps++;
        }

        assertNotNull(last, "O último estado não deve ser nulo.");
        // A lógica de formatação do Board depende da sua implementação
        assertEquals(goal, new Board(last.toString().replace("\n", "").replace(" ", "0")), "O estado final deve ser o objetivo.");
        assertEquals(12.0, last.getG(), "O custo total deve ser 12.0.");
        assertEquals(13, steps, "Deve haver 5 estados no caminho.");
    }

    @Test
    public void testAlreadySolvedPuzzle() {
        Ilayout initial = new Board("123405678"); // já é o objetivo
        Ilayout goal = new Board("123405678");

        BestFirst solver = new BestFirst();
        Iterator<BestFirst.State> solution = solver.solve(initial, goal);

        assertNotNull(solution, "Solução não deveria ser nula");

        BestFirst.State last = null;
        int steps = 0;
        while (solution.hasNext()) {
            last = solution.next();
            steps++;
        }

        assertEquals(1, steps, "Se já está resolvido, deve haver apenas 1 estado");
        assertEquals(0.0, last.getG(), "Custo deve ser 0.0");
        System.out.println("Teste 'testAlreadySolvedPuzzle' concluído com sucesso!");
    }
    
    @Test
    public void testUnsolvablePuzzle() {
        // Exemplo de um estado irresolúvel para o quebra-cabeça 8-puzzle
        Ilayout initial = new Board("812043765");
        Ilayout goal = new Board("123456780");

        BestFirst solver = new BestFirst();
        Iterator<BestFirst.State> solution = solver.solve(initial, goal);

        assertNull(solution, "A solução deve ser nula para um quebra-cabeça irresolúvel.");
    }
}
