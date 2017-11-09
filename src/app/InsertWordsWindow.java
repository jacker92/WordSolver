package app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class InsertWordsWindow {
    
    private Label label;
    private TextField solvableTextField;
    private Button button;
    private GridPane stuff;
    private FlowPane root;
    
    public InsertWordsWindow() {
        this.stuff = new GridPane();
        this.label = new Label("Please Enter a Word");
        this.solvableTextField = new TextField();
        this.button = new Button("Solve"); 
        this.root = new FlowPane();
    }

    public Parent getParent() {
     
        stuff.add(label, 0, 0);
        stuff.add(solvableTextField, 0, 1);
        stuff.add(button, 0, 2);
        
        stuff.setHgap(20);
        stuff.setVgap(20);
        stuff.setPadding(new Insets(20,20,20,20));
        root.getChildren().add(stuff);
        root.setAlignment(Pos.CENTER);
        
        return root;
    }

    public Label getLabel() {
        return label;
    }

    public TextField getSolvableTextField() {
        return solvableTextField;
    }

    public Button getButton() {
        return button;
    }

    public GridPane getStuff() {
        return stuff;
    }
    
    
    
    
}
