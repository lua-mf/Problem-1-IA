package puzzle.tests;

import org.junit.jupiter.api.Test;
import puzzle.core.Board;
import puzzle.core.Ilayout;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChildrenTests {

    @Test
    public void testChildrenCountSimple() {
        // Estado: zero no canto superior esquerdo
        Board b = new Board("023145678");
        List<Ilayout> children = b.children();

        // Zero no canto pode mover apenas para baixo e para direita
        assertEquals(2, children.size(), "Zero no canto deve gerar 2 filhos.");
    }

    @Test
    public void testChildrenCorrectBoards() {
        // Estado: zero no meio
        Board b = new Board("123405678");
        List<Ilayout> children = b.children();

        // Deve haver 4 movimentos possíveis
        assertEquals(4, children.size(), "Zero no meio deve gerar 4 filhos.");

        // Flags para verificar os movimentos
        boolean foundLeft = false, foundRight = false, foundUp = false, foundDown = false;

        for (Ilayout child : children) {
            String boardStr = child.toString();
            System.out.println("Filho gerado:\n" + boardStr); // DEBUG VISUAL

            if (boardStr.equals("123\n 45\n678\n")) foundLeft = true;   // zero move left
            if (boardStr.equals("123\n45 \n678\n")) foundRight = true;  // zero move right
            if (boardStr.equals("1 3\n425\n678\n")) foundUp = true;     // zero move up
            if (boardStr.equals("123\n475\n6 8\n")) foundDown = true;   // zero move down
        }

        assertTrue(foundLeft, "Movimento para esquerda esperado.");
        assertTrue(foundRight, "Movimento para direita esperado.");
        assertTrue(foundUp, "Movimento para cima esperado.");
        assertTrue(foundDown, "Movimento para baixo esperado.");
    }
}
