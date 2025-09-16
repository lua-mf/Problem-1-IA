package puzzle.tests;

import org.junit.jupiter.api.Test;
import puzzle.core.Board;

import static org.junit.jupiter.api.Assertions.*;

public class PuzzleUnitTests {

    @Test
    public void testConstructorAndToString() {
        Board b = new Board("023145678");
        String expected =
                        " 23\n" +
                        "145\n" +
                        "678\n";
        assertEquals(expected, b.toString());
    }

    @Test
    public void testConstructorDifferentZeroPosition() {
        Board b = new Board("123485670");
        String expected =
                "123\n" +
                        "485\n" +
                        "67 \n";
        assertEquals(expected, b.toString());
    }

    @Test
    public void testEqualsSameBoard() {
        Board b1 = new Board("123405678");
        Board b2 = new Board("123405678");
        assertEquals(b1, b2); // Devem ser iguais
    }

    @Test
    public void testEqualsDifferentBoard() {
        Board b1 = new Board("123405678");
        Board b2 = new Board("123450678");
        assertNotEquals(b1, b2); // Devem ser diferentes
    }

    @Test
    public void testToStringFormat() {
        Board b = new Board("123405678");
        String expected =
                        "123\n" +
                        "4 5\n" +
                        "678\n";
        assertEquals(expected, b.toString());
    }
}