package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.comp301.a09akari.SamplePuzzles.*;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI
    PuzzleLibrary library = new PuzzleLibraryImpl();
    library.addPuzzle(new PuzzleImpl(PUZZLE_01));
    library.addPuzzle(new PuzzleImpl(PUZZLE_02));
    library.addPuzzle(new PuzzleImpl(PUZZLE_03));
    library.addPuzzle(new PuzzleImpl(PUZZLE_04));
    library.addPuzzle(new PuzzleImpl(PUZZLE_05));
    Model model = new ModelImpl(library);
    ClassicMvcController controller = new ControllerImpl(model);
    VBox layout = new VBox();
    PuzzleIndexView piv = new PuzzleIndexView(model);
    PuzzleView pv = new PuzzleView(model, controller);
    ControlView cv = new ControlView(controller);
    MessageView mv = new MessageView(model, controller);
    layout.getChildren().add(piv.render());
    layout.getChildren().add(pv.render());
    layout.getChildren().add(cv.render());
    layout.getChildren().add(mv.render());
    ModelObserver observer = (Model m) -> {
      VBox layout1 = new VBox();
      layout1.getChildren().add(piv.render());
      layout1.getChildren().add(pv.render());
      layout1.getChildren().add(cv.render());
      layout1.getChildren().add(mv.render());
      Scene scene1 = new Scene(layout1);
      scene1.getStylesheets().add("main.css");
      stage.setScene(scene1);
      stage.show();
    };
    model.addObserver(observer);
    Scene scene = new Scene(layout);
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);
    stage.show();

  }
}
