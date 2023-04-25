package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MessageView implements FXComponent {
  private final Model model;
  private final ClassicMvcController controller;

  public MessageView(Model model, ClassicMvcController controller) {
    this.model = model;
    this.controller = controller;
  }

  @Override
  public Parent render() {
    StackPane layout = new StackPane();
    layout.setAlignment(Pos.CENTER);
    if (model.isSolved()) {
      Label label = new Label("Congratulations! You have successfully completed the puzzle!");
      label.setTextFill(Color.BLACK);
      label.setFont(new Font("Arial", 12));
      layout.getChildren().add(label);
      layout.getStyleClass().add("message");
    }
    return layout;
  }
}
