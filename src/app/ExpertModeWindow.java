package app;

import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class ExpertModeWindow {

    private ListView listView;
    private ComboBox wordLengthComboBox;
    private Button searchButton;
    private Button backToNormalModeButton;
    private Label wordLengthLabel;
    private Solver solver;
    private Label languageLabel;
    private ComboBox languageComboBox;

    public ExpertModeWindow(Solver ratkaisin) {
        this.listView = new ListView();
        this.wordLengthComboBox = new ComboBox();
        this.searchButton = new Button("Search");
        this.backToNormalModeButton = new Button("Back to normal mode");
        this.wordLengthLabel = new Label("Word length");
        this.solver = ratkaisin;
        this.languageLabel = new Label("Select language");
        this.languageComboBox = new ComboBox();
        
    }

    protected Parent getParent() {
        FlowPane pane = new FlowPane();
        GridPane grid = new GridPane();

        grid.setVgap(10);
        grid.setHgap(10);
        
        languageComboBox.getItems().add("Finnish");
        languageComboBox.getItems().add("Gibberish");
        languageComboBox.getSelectionModel().selectFirst();
        
        listView.setPrefSize(100, 100);
        grid.add(wordLengthLabel, 0, 0);
        grid.add(wordLengthComboBox, 1, 0);
        grid.add(languageLabel, 0, 1);
        grid.add(languageComboBox, 1, 1);
        grid.add(listView, 0, 2);
        grid.add(searchButton, 0, 3);
        grid.add(backToNormalModeButton, 1, 3);

        pane.setAlignment(Pos.CENTER);

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

    protected void setComboBoxItems() {
        int count = solver.getWord().length();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            list.add(i);
        }
        wordLengthComboBox.getItems().clear();
        wordLengthComboBox.getItems().addAll(list);
    }

    protected void setListViewItems(List<String> list) {
        listView.getItems().clear();
        listView.getItems().setAll(list);
    }

    public Solver getSolver() {
        return solver;
    }

    public void changeView() {
        setComboBoxItems();
          wordLengthComboBox.getSelectionModel().selectFirst();
        listView.getItems().clear();
    }

    public ComboBox getLanguageComboBox() {
        return languageComboBox;
    }
}
