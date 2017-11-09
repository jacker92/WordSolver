package app;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class ExpertModeWindow {

    private ListView listView;
    private ComboBox wordLengthComboBox;
    private Button searchButton;
    private Button backToNormalModeButton;
    private Label wordLengthLabel;

    public ExpertModeWindow() {
        this.listView = new ListView();
        this.wordLengthComboBox = new ComboBox();
        this.searchButton = new Button("Search");
        this.backToNormalModeButton = new Button("Back to normal mode");
        this.wordLengthLabel = new Label("Length");
    }

    public Parent getParent() {
        FlowPane pane = new FlowPane();
        GridPane grid = new GridPane();

        grid.setVgap(10);
        grid.setHgap(10);

        listView.setPrefSize(100, 100);
        grid.add(wordLengthLabel, 0, 0);
        grid.add(wordLengthComboBox, 1, 0);
        grid.add(listView, 0, 2);
        grid.add(searchButton, 0, 3);
        grid.add(backToNormalModeButton, 0, 4);
  
        grid.setAlignment(Pos.CENTER);

        pane.getChildren().add(grid);
      
        return pane;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public Button getBackToNormalModeButton() {
        return backToNormalModeButton;
    }

    public ListView getListView() {
        return listView;
    }

    public ComboBox getWordLengthComboBox() {
        return wordLengthComboBox;
    }

    public Label getWordLengthLabel() {
        return wordLengthLabel;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public void setWordLengthComboBox(ComboBox wordLengthComboBox) {
        this.wordLengthComboBox = wordLengthComboBox;
    }

    public void setSearchButton(Button searchButton) {
        this.searchButton = searchButton;
    }

    public void setBackToNormalModeButton(Button backToNormalModeButton) {
        this.backToNormalModeButton = backToNormalModeButton;
    }

    public void setWordLengthLabel(Label wordLengthLabel) {
        this.wordLengthLabel = wordLengthLabel;
    }
    
    

}
