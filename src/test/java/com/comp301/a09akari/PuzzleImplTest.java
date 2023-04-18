package com.comp301.a09akari;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Puzzle;
import com.comp301.a09akari.model.PuzzleImpl;
import org.junit.Test;

import static com.comp301.a09akari.SamplePuzzles.PUZZLE_05;
import static org.junit.Assert.*;

public class PuzzleImplTest {

    @Test
    public void testConstructorNormal(){
        int[][] PUZZLE_05 = {
                {6, 6, 5, 6, 6, 6},
                {6, 5, 6, 6, 6, 3},
                {6, 6, 6, 6, 6, 6},
                {6, 6, 6, 6, 6, 6},
                {3, 6, 6, 6, 6, 6},
                {6, 2, 6, 6, 6, 6},
                {6, 6, 6, 6, 0, 6},
        };
        Puzzle puzzle = new PuzzleImpl(PUZZLE_05);
        assertNotNull(puzzle);
        assertEquals(7, puzzle.getHeight());
        assertEquals(6, puzzle.getWidth());
    }
}

