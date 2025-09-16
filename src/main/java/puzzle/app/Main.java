package puzzle.app;

import puzzle.core.Board;

public class Main {
    public static void main(String[] args) {
        Board initial = new Board("023145678");
        Board goal = new Board("123405678");

        System.out.println("Initial board:");
        initial.printBoard();

        System.out.println("Goal board:");
        goal.printBoard();
    }
}