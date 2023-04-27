package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class PuzzleView implements FXComponent {
  private final Model model;
  private final ClassicMvcController controller;

  public PuzzleView(Model model, ClassicMvcController controller) {
    this.model = model;
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane layout = new GridPane();
    layout.setAlignment(Pos.CENTER);
    layout.setPrefWidth(400);
    layout.setPrefHeight(320);
    Puzzle activePuzzle = model.getActivePuzzle();
    for (int i = 0; i < activePuzzle.getHeight(); i++) {
      for (int j = 0; j < activePuzzle.getWidth(); j++) {
        if (activePuzzle.getCellType(i, j) == CellType.WALL) {
          HBox wall = makeWall();
          layout.add(wall, i, j);
        } else if (activePuzzle.getCellType(i, j) == CellType.CLUE
            && !model.isClueSatisfied(i, j)) {
          HBox unsatisfiedClue = makeUnsatisfiedClue(activePuzzle.getClue(i, j));
          layout.add(unsatisfiedClue, i, j);
        } else if (activePuzzle.getCellType(i, j) == CellType.CLUE && model.isClueSatisfied(i, j)) {
          HBox satisfiedClue = makeSatisfiedClue(activePuzzle.getClue(i, j));
          layout.add(satisfiedClue, i, j);
        } else if (activePuzzle.getCellType(i, j) == CellType.CORRIDOR && !model.isLit(i, j)) {
          Button unlitCorridor = makeUnlitCorridor(i, j);
          layout.add(unlitCorridor, i, j);
        } else if (activePuzzle.getCellType(i, j) == CellType.CORRIDOR
            && model.isLit(i, j)
            && !model.isLamp(i, j)) {
          Button litCorridor = makeLitCorridor(i, j);
          layout.add(litCorridor, i, j);
        } else if (activePuzzle.getCellType(i, j) == CellType.CORRIDOR
            && model.isLamp(i, j)
            && !model.isLampIllegal(i, j)) {
          Button corridorwithLamp = makeCorridorWithLamp(i, j);
          layout.add(corridorwithLamp, i, j);
        } else if (activePuzzle.getCellType(i, j) == CellType.CORRIDOR
            && model.isLampIllegal(i, j)) {
          Button corridorWithIllegalLamp = makeCorridorWithIllegalLamp(i, j);
          layout.add(corridorWithIllegalLamp, i, j);
        }
      }
    }
    layout.getStyleClass().add("grid");
    return layout;
  }

  private Button makeUnlitCorridor(int r, int c) {
    Button button = new Button();
    button.setPrefSize(30, 30);
    button.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickCell(r, c);
          }
        });
    button.getStyleClass().add("unlit-corridor");
    return button;
  }

  private Button makeLitCorridor(int r, int c) {
    Button button = new Button();
    button.setPrefSize(30, 30);
    button.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickCell(r, c);
          }
        });
    button.getStyleClass().add("lit-corridor");
    return button;
  }

  private Button makeCorridorWithLamp(int r, int c) {
    Button button = new Button();
    button.setPrefSize(30, 30);
    Image img = new Image("light-bulb.png");
    ImageView view = new ImageView(img);
    view.setPreserveRatio(true);
    view.setFitWidth(12.5);
    view.setFitHeight(12.5);
    button.setGraphic(view);
    button.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickCell(r, c);
          }
        });
    button.getStyleClass().add("corridor-lamp");
    return button;
  }

  private Button makeCorridorWithIllegalLamp(int r, int c) {
    Button button = new Button();
    button.setPrefSize(30, 30);
    Image img = new Image("light-bulb.png");
    ImageView view = new ImageView(img);
    view.setPreserveRatio(true);
    view.setFitHeight(12.5);
    view.setFitWidth(12.5);
    button.setGraphic(view);
    button.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickCell(r, c);
          }
        });
    button.getStyleClass().add("corridor-illegal-lamp");
    return button;
  }

  private HBox makeWall() {
    HBox wall = new HBox();
    wall.getStyleClass().add("wall");
    return wall;
  }

  private HBox makeUnsatisfiedClue(int num) {
    HBox box = new HBox();
    Label label = new Label(String.valueOf(num));
    label.setTextFill(Color.RED);
    box.getChildren().add(label);
    box.setAlignment(Pos.CENTER);
    box.getStyleClass().add("clue-unsatisfied");
    return box;
  }

  private HBox makeSatisfiedClue(int num) {
    HBox box = new HBox();
    Label label = new Label(String.valueOf(num));
    label.setTextFill(Color.WHITE);
    box.getChildren().add(label);
    box.setAlignment(Pos.CENTER);
    box.getStyleClass().add("clue-satisfied");
    return box;
  }
}
