package com.comp301.a09akari.view;

import com.comp301.a09akari.model.Model;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class PuzzleIndexView implements FXComponent {
  private final Model model;

  public PuzzleIndexView(Model model) {
    this.model = model;
  }

  @Override
  public Parent render() {
    StackPane layout = new StackPane();
    int puzzleIndex = model.getActivePuzzleIndex() + 1;
    Label label = new Label("Puzzle " + puzzleIndex + " of " + model.getPuzzleLibrarySize());
    layout.getChildren().add(label);
    return layout;
  }
}
