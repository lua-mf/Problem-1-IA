package puzzle.core;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// Classe que representa o tabuleiro do puzzle
public class Board implements Ilayout, Cloneable {
    private static final int SIZE = 3; // tamanho do tabuleiro 3x3
    private int[][] boardMatrix;

    // Construtor: cria o tabuleiro a partir de uma string (ex: "123405678")
    public Board(String str) {
        if (str.length() != SIZE * SIZE)
            throw new IllegalStateException("String inválida para tabuleiro");

        boardMatrix = new int[SIZE][SIZE];
        int pos = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                boardMatrix[i][j] = Character.getNumericValue(str.charAt(pos++));
            }
        }
    }

    // Representação do tabuleiro em formato de string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(boardMatrix[i][j] == 0 ? " " : boardMatrix[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Verifica se dois tabuleiros são iguais
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Board)) return false;
        Board other = (Board) obj;
        return Arrays.deepEquals(this.boardMatrix, other.boardMatrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(boardMatrix);
    }

    // Implementação: gera todos os filhos válidos movendo o zero
    @Override
    public List<Ilayout> children() {
        List<Ilayout> result = new ArrayList<>();

        // Localiza o zero
        int zeroI = -1, zeroJ = -1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (boardMatrix[i][j] == 0) {
                    zeroI = i;
                    zeroJ = j;
                    break;
                }
            }
        }

        // Movimentos possíveis (cima, baixo, esquerda, direita)
        int[][] moves = { {-1,0}, {1,0}, {0,-1}, {0,1} };
        for (int[] move : moves) {
            int newI = zeroI + move[0];
            int newJ = zeroJ + move[1];
            if (isValid(newI, newJ)) {
                Board newBoard = cloneBoard();
                // Troca zero com a posição destino
                newBoard.boardMatrix[zeroI][zeroJ] = newBoard.boardMatrix[newI][newJ];
                newBoard.boardMatrix[newI][newJ] = 0;
                result.add(newBoard);
            }
        }

        return result;
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && i < SIZE && j >= 0 && j < SIZE;
    }

    private Board cloneBoard() {
        Board clone = new Board("000000000");
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(this.boardMatrix[i], 0, clone.boardMatrix[i], 0, SIZE);
        }
        return clone;
    }

    @Override
    public boolean isGoal(Ilayout l) {
        return this.equals(l);
    }

    @Override
    public double getK() {
        return 1.0;
    }

    // Imprime o tabuleiro de forma mais "visual"
    public void printBoard() {
        System.out.println(toString());
        System.out.println("-----------");
    }
}