package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class ShowResultsWindow {

    private ListView listView;
    private Button backButton;
    private Button expertButton;
    private TextField characterCountTextField;
    private Label filterLabel;
    private Ratkaisin ratkaisin;
    private String word;
    private List<String> allPossibleWords;

    public ShowResultsWindow() {

        listView = new ListView();
        backButton = new Button("Enter New Word");
        expertButton = new Button("Expert Mode");
        characterCountTextField = new TextField();
        filterLabel = new Label("Filter by number of characters");
        ratkaisin = new Ratkaisin();
    }

    public Parent getParent() {
        FlowPane pane = new FlowPane();
        GridPane grid = new GridPane();
        
        grid.setVgap(10);
        grid.setHgap(10);
        
        listView.setPrefSize(100, 100);

        grid.add(listView, 0, 0);
        grid.add(filterLabel, 0, 1);
        grid.add(characterCountTextField, 0, 2);
        grid.add(backButton, 0, 3);
        grid.add(expertButton, 1, 3);

        pane.getChildren().add(grid);
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    public ListView getListView() {
        return listView;
    }
    
    public void setWord(String word) {
        this.word = word;
    }

    public Set<String> solveWord() {
        return this.ratkaisin.ratkaiseSana(this.word, 10000);
    }

    public Button getBackButton() {
        return backButton;
    }

    public TextField getCharacterCountTextField() {
        return characterCountTextField;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }
    
    public List<String> getAllPossibleWords() {
        return allPossibleWords;
    }

    public String getWord() {
        return word;
    }

    public Button getExpertButton() {
        return expertButton;
    }

    public Ratkaisin getRatkaisin() {
        return ratkaisin;
    }
    
   public void processWord() {
      Set<String> list =  ratkaisin.ratkaiseSana(this.word, 10000);
     allPossibleWords = list.stream().filter(s -> ratkaisin.onSuomea(s))
    .collect(Collectors.toCollection(ArrayList::new));
     Collections.sort(allPossibleWords, new ComparatorByLength());
     listView.getItems().clear();
     listView.getItems().addAll(allPossibleWords);
    }

}
