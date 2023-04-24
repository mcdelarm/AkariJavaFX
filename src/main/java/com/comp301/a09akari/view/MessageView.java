package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class MessageView implements FXComponent {
    private Model model;
    private ClassicMvcController controller;

    public MessageView(Model model, ClassicMvcController controller) {
        this.model = model;
        this.controller = controller;
    }
    @Override
    public Parent render() {
        StackPane layout = new StackPane();
        if (model.isSolved()) {
            Label label = new Label("Congratulations! You have successfully completed the puzzle!");
            layout.getChildren().add(label);
        }
        return layout;
    }
}
