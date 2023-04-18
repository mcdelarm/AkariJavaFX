package com.comp301.a09akari;

import com.comp301.a09akari.model.*;
import org.junit.Test;

import static com.comp301.a09akari.SamplePuzzles.*;
import static org.junit.Assert.*;
public class ModelImplTest {
    @Test
    public void testModelImpl() {
        PuzzleLibrary library = new PuzzleLibraryImpl();
        library.addPuzzle(new PuzzleImpl(PUZZLE_01));
        library.addPuzzle(new PuzzleImpl(PUZZLE_02));
        library.addPuzzle(new PuzzleImpl(PUZZLE_03));
        library.addPuzzle(new PuzzleImpl(PUZZLE_04));
        library.addPuzzle(new PuzzleImpl(PUZZLE_05));
        Model model = new ModelImpl(library);
        assertNotNull(model);
        assertEquals(0, model.getActivePuzzleIndex());
        model.addLamp(0, 0);
        assertTrue(model.isLamp(0, 0));
        model.removeLamp(0, 0);
        assertFalse(model.isLamp(0, 0));
        model.addLamp(0, 0);
        assertTrue(model.isLit(0, 3));
        assertFalse(model.isLit(0, 5));
        model.addLamp(0, 1);
        model.removeLamp(0, 0);
        model.removeLamp(0, 1);
        model.addLamp(0, 3);
        assertTrue(model.isClueSatisfied(0, 4));
        assertEquals(0, model.getActivePuzzleIndex());
        model.setActivePuzzleIndex(3);
        assertEquals(3, model.getActivePuzzleIndex());
    }
}
