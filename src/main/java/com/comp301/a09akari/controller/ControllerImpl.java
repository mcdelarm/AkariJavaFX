package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;

public class ControllerImpl implements ClassicMvcController {
  private final Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  private int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }

  @Override
  public void clickNextPuzzle() {
    int newIndex = (model.getActivePuzzleIndex() + 1) % model.getPuzzleLibrarySize();
    model.setActivePuzzleIndex(newIndex);
  }

  @Override
  public void clickPrevPuzzle() {
    if (model.getActivePuzzleIndex() == 0) {
      model.setActivePuzzleIndex(model.getPuzzleLibrarySize() - 1);
    } else {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    }
  }

  @Override
  public void clickRandPuzzle() {
    int random = getRandomNumber(0, model.getPuzzleLibrarySize() - 1);
    while (random == model.getActivePuzzleIndex()) {
      random = getRandomNumber(0, model.getPuzzleLibrarySize() - 1);
    }
    model.setActivePuzzleIndex(random);
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (model.isLamp(r, c)) {
      model.removeLamp(r, c);
    } else {
      model.addLamp(r, c);
    }
  }
}
