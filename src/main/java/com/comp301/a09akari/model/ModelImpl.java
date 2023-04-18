package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
    private PuzzleLibrary library;
    private int index;
    private int[][] lampLocations;
    List<ModelObserver> observers;


    public ModelImpl(PuzzleLibrary library) {
        if (library == null) {
            throw new IllegalArgumentException();
        }
        this.library = library;
        this.index = 0;
        this.lampLocations = new int[getActivePuzzle().getHeight()][getActivePuzzle().getWidth()];
        this.observers= new ArrayList<>();

    }

    @Override
    public void addLamp(int r, int c) {
        if (r >= library.getPuzzle(index).getHeight() || c >= library.getPuzzle(index).getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (library.getPuzzle(index).getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        lampLocations[r][c] = 1;
        for (ModelObserver observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void removeLamp(int r, int c) {
        if (r >= library.getPuzzle(index).getHeight() || c >= library.getPuzzle(index).getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (library.getPuzzle(index).getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        lampLocations[r][c] = 0;
        for (ModelObserver observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public boolean isLit(int r, int c) {
        if (r >= library.getPuzzle(index).getHeight() || c >= library.getPuzzle(index).getWidth() || r < 0 || c < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (library.getPuzzle(index).getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        if (isLamp(r, c)) {
            return true;
        }
        // Check Left of cell location
        int i = c;
        while (i >= 0 && getActivePuzzle().getCellType(r, i) == CellType.CORRIDOR) {
            if (isLamp(r, i)) {
                return true;
            }
            i--;
        }
        // Check Right of cell location
        i = c;
        while (i <= getActivePuzzle().getWidth() - 1 && getActivePuzzle().getCellType(r, i) == CellType.CORRIDOR) {
            if (isLamp(r, i)) {
                return true;
            }
            i++;
        }
        // Check Above cell location
        int j = r;
        while (j >= 0 && getActivePuzzle().getCellType(j, c) == CellType.CORRIDOR) {
            if (isLamp(j, c)) {
                return true;
            }
            j--;
        }
        // Check Below cell location
        j = r;
        while (j <= getActivePuzzle().getHeight() - 1 && getActivePuzzle().getCellType(j, c) == CellType.CORRIDOR) {
            if (isLamp(j, c)) {
                return true;
            }
            j++;
        }
        return false;
    }

    @Override
    public boolean isLamp(int r, int c) {
        if (r < 0 || c < 0 || r >= library.getPuzzle(index).getHeight() || c >= library.getPuzzle(index).getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (library.getPuzzle(index).getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        if (lampLocations[r][c] == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isLampIllegal(int r, int c) {
        if (r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (isLamp(r, c) == false) {
            throw new IllegalArgumentException();
        }
        // Check Left of Lamp
        int i = c - 1;
        while (i >= 0 && getActivePuzzle().getCellType(r, i) == CellType.CORRIDOR) {
            if (isLamp(r, i)) {
                return true;
            }
            i--;
        }
        // Check Right of Lamp
        i = c + 1;
        while (i <= getActivePuzzle().getWidth() - 1 && getActivePuzzle().getCellType(r, i) == CellType.CORRIDOR) {
            if (isLamp(r, i)) {
                return true;
            }
            i++;
        }
        // Check Above Lamp
        int j = r - 1;
        while (j >= 0 && getActivePuzzle().getCellType(j, c) == CellType.CORRIDOR) {
            if (isLamp(j, c)) {
                return true;
            }
            j--;
        }
        // Check Below Lamp
        j = r + 1;
        while (j <= getActivePuzzle().getHeight() - 1 && getActivePuzzle().getCellType(j, c) == CellType.CORRIDOR) {
            if (isLamp(j, c)) {
                return true;
            }
            j++;
        }
        return false;
    }

    @Override
    public Puzzle getActivePuzzle() {
        return library.getPuzzle(index);
    }

    @Override
    public int getActivePuzzleIndex() {
        return this.index;
    }

    @Override
    public void setActivePuzzleIndex(int index) {
        if (index >= this.getPuzzleLibrarySize() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.index = index;
        lampLocations = new int[getActivePuzzle().getHeight()][getActivePuzzle().getWidth()];
        for (ModelObserver observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public int getPuzzleLibrarySize() {
        return library.size();
    }

    @Override
    public void resetPuzzle() {
        for (int i = 0; i < lampLocations.length; i++) {
            for (int j = 0; j < lampLocations[i].length; j++) {
                lampLocations[i][j] = 0;
            }
        }
        for (ModelObserver observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public boolean isSolved() {
        int height = getActivePuzzle().getHeight();
        int width = getActivePuzzle().getWidth();
        for (int i = 0;  i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR) {
                    if (isLit(i, j) == false) {
                        return false;
                    }
                    if (isLamp(i, j)) {
                        if (isLampIllegal(i, j)) {
                            return false;
                        }
                    }
                }
                if (getActivePuzzle().getCellType(i, j) == CellType.CLUE) {
                    if (isClueSatisfied(i, j) == false) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean isClueSatisfied(int r, int c) {
        if (r < 0 || c < 0 || r >= library.getPuzzle(index).getHeight() || c >= library.getPuzzle(index).getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (library.getPuzzle(index).getCellType(r, c) != CellType.CLUE) {
            throw new IllegalArgumentException();
        }
        int clueValue = getActivePuzzle().getClue(r, c);
        int count = 0;
        try {
            boolean case1 = isLamp(r-1, c);
            if (case1) {
                count++;
            }
        }
        catch (IndexOutOfBoundsException e){
        }
        catch (IllegalArgumentException oe){
        }

        try{
            boolean case2 = isLamp(r, c-1);
            if (case2) {
                count++;
            }
        }
        catch (IndexOutOfBoundsException e){
        }
        catch (IllegalArgumentException oe){
        }

        try{
            boolean case3 = isLamp(r+1, c);
            if (case3) {
                count++;
            }
        }
        catch (IndexOutOfBoundsException e){
        }
        catch (IllegalArgumentException oe){
        }

        try{
            boolean case4 = isLamp(r, c+1);
            if (case4) {
                count++;
            }
        }
        catch (IndexOutOfBoundsException e){
        }
        catch (IllegalArgumentException oe){
        }

        if (count == clueValue) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }
}
