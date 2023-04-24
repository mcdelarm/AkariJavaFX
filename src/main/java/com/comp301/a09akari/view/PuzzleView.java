package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;
import com.comp301.a09akari.model.PuzzleImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class PuzzleView implements FXComponent{
    private Model model;
    private ClassicMvcController controller;

    public PuzzleView(Model model, ClassicMvcController controller) {
        this.model = model;
        this.controller = controller;
    }
    @Override
    public Parent render() {
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPrefWidth(250);
        layout.setMinWidth(250);
        Puzzle activePuzzle = model.getActivePuzzle();
        for (int i = 0 ; i < activePuzzle.getHeight(); i++) {
            for (int j = 0; j < activePuzzle.getWidth(); j++) {
                if (activePuzzle.getCellType(i, j) == CellType.WALL) {
                    layout.add(makeWall(), i, j);
                }
                else if (activePuzzle.getCellType(i, j) == CellType.CLUE && model.isClueSatisfied(i, j) == false) {
                    layout.add(makeUnsatisfiedClue(activePuzzle.getClue(i, j)), i, j);
                }
                else if (activePuzzle.getCellType(i, j) == CellType.CLUE && model.isClueSatisfied(i, j)) {
                    layout.add(makeSatisfiedClue(activePuzzle.getClue(i, j)), i, j);
                }
                else if (activePuzzle.getCellType(i, j) == CellType.CORRIDOR && model.isLit(i, j) == false) {
                    layout.add(makeUnlitCorridor(i, j), i, j);
                }
                else if (activePuzzle.getCellType(i, j) == CellType.CORRIDOR && model.isLit(i, j) && model.isLamp(i, j) == false) {
                    layout.add(makeLitCorridor(i, j), i, j);
                }
                else if (activePuzzle.getCellType(i, j) == CellType.CORRIDOR && model.isLamp(i, j) && model.isLampIllegal(i, j) == false) {
                    layout.add(makeCorridorWithLamp(i, j), i, j);
                }
                else if (activePuzzle.getCellType(i, j) == CellType.CORRIDOR && model.isLampIllegal(i, j)) {
                    layout.add(makeCorridorWithIllegalLamp(i, j), i, j);
                }
            }
        }
        layout.getStyleClass().add("grid");
        return layout;
    }

    private Button makeUnlitCorridor(int r, int c) {
        Button button = new Button();
        button.setPrefSize(30, 30);
        button.setOnAction(new EventHandler<ActionEvent>() {
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
        button.setOnAction(new EventHandler<ActionEvent>() {
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
        button.setOnAction(new EventHandler<ActionEvent>() {
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
        button.setOnAction(new EventHandler<ActionEvent>() {
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
