package puzzle.core;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// Classe que representa o tabuleiro do puzzle
public class Board implements Ilayout, Cloneable {
    private static final int DIM = 3; // tamanho do tabuleiro 3x3
    private int[][] boardMatrix;

    // Construtor vazio
    public Board() {
        boardMatrix = new int[DIM][DIM];
    }

    // Construtor a partir de uma string (ex: "023145678")
    public Board(String str) throws IllegalStateException {
        if (str.length() != DIM * DIM) {
            throw new IllegalStateException("String inválida para o Board");
        }
        boardMatrix = new int[DIM][DIM];
        int k = 0;
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                boardMatrix[i][j] = Character.getNumericValue(str.charAt(k++));
            }
        }
    }

    // Implementação: gera todos os filhos válidos movendo o zero
    @Override
    public List<Ilayout> children() {
        List<Ilayout> result = new ArrayList<>();

        // Localiza o zero
        int zeroI = -1, zeroJ = -1;
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                // se o valor for zero, imprime espaço em branco
                sb.append(boardMatrix[i][j] == 0 ? " " : boardMatrix[i][j]);
            }
            sb.append("\n"); // quebra de linha no final de cada linha do tabuleiro
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

    private boolean isValid(int i, int j) {
        return i >= 0 && i < DIM && j >= 0 && j < DIM;
    }

    private Board cloneBoard() {
        Board clone = new Board("000000000");
        for (int i = 0; i < DIM; i++) {
            System.arraycopy(this.boardMatrix[i], 0, clone.boardMatrix[i], 0, DIM);
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