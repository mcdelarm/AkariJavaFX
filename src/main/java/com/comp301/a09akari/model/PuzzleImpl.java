package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle{
    private int[][] board;

    public PuzzleImpl(int[][] board) {
        if (board == null) {
            throw new IllegalArgumentException();
        }
        this.board = board;
    }

    @Override
    public int getWidth() {
        return board[0].length;
    }

    @Override
    public int getHeight() {
        return board.length;
    }

    @Override
    public CellType getCellType(int r, int c) {
        if (r > this.getHeight() - 1 || c > this.getWidth() - 1) {
            throw new IndexOutOfBoundsException();
        }
        int value = board[r][c];
        if (value == 0 || value == 1 || value == 2 || value == 3 || value == 4) {
            return CellType.CLUE;
        }
        else if (value == 5) {
            return CellType.WALL;
        }
        else {
            return CellType.CORRIDOR;
        }
    }

    @Override
    public int getClue(int r, int c) {
        if (r > this.getHeight() - 1 || c > this.getWidth() - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (this.getCellType(r, c) != CellType.CLUE) {
            throw new IllegalArgumentException();
        }
        return board[r][c];
    }
}
