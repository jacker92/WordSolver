package app;

import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class InsertWordsWindow {

    private Label label;
    private TextField solvableTextField;
    private Button button;
    private GridPane gridPane;
    private FlowPane root;

    public InsertWordsWindow() {
        this.gridPane = new GridPane();
        this.label = new Label("Please Enter a Word");
        this.solvableTextField = new TextField();
        this.button = new Button("Solve");
        this.root = new FlowPane();
    }

    public Parent getParent() {

        gridPane.add(label, 0, 0);
        gridPane.add(solvableTextField, 0, 1);
        gridPane.add(button, 0, 2);
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        root.getChildren().add(gridPane);
        root.setAlignment(Pos.CENTER);

        return root;
    }

    public TextField getSolvableTextField() {
        return solvableTextField;
    }

    public Button getButton() {
        return button;
    }

    public void changeView() {
        solvableTextField.setText("");
        solvableTextField.requestFocus();
    }
}
