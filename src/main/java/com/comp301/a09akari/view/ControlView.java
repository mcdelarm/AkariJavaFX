package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlView implements FXComponent {
  private final ClassicMvcController controller;

  public ControlView(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();
    Button resetButton = new Button("Reset Puzzle");
    layout.getChildren().add(resetButton);
    resetButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickResetPuzzle();
          }
        });
    Button nextButton = new Button("Next Puzzle");
    layout.getChildren().add(nextButton);
    nextButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickNextPuzzle();
          }
        });
    Button previousButton = new Button("Previous Puzzle");
    layout.getChildren().add(previousButton);
    previousButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickPrevPuzzle();
          }
        });
    Button randomPuzzle = new Button("Random Puzzle");
    layout.getChildren().add(randomPuzzle);
    randomPuzzle.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickRandPuzzle();
          }
        });
    return layout;
  }
}
