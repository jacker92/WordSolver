package app;

import java.util.*;
import java.util.stream.Collectors;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ShowResultsWindow {

    private ListView listView;
    private Button backButton;
    private Button expertButton;
    private TextField characterCountTextField;
    private Label filterLabel;
    private Solver solver;
    private List<String> allPossibleWords;

    public ShowResultsWindow() {

        listView = new ListView();
        backButton = new Button("Enter New Word");
        expertButton = new Button("Expert Mode");
        characterCountTextField = new TextField();
        filterLabel = new Label("Filter by number of characters");
        solver = new Solver();
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

    public Button getExpertButton() {
        return expertButton;
    }

    public Solver getSolver() {
        return solver;
    }

    public void processWord(String word) {
        Set<String> list = solver.solveWord(word, 10000);
        allPossibleWords = list.stream().filter(s -> solver.isFinnish(s))
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(allPossibleWords, new ComparatorByLength());
        listView.getItems().clear();
        listView.getItems().addAll(allPossibleWords);
    }

    public void listViewScrollBarReset() {
        // Try-catch because scrollbar might not be there
        try {
        ScrollBar scroll = (ScrollBar) this.getListView().lookupAll(".scroll-bar").iterator().next();
        scroll.setValue(0);
        } catch (Exception e) {
            
        }
    }

    public void changeView(String solvableWord) {
        getCharacterCountTextField().requestFocus();
        processWord(solvableWord);
        listViewScrollBarReset();
    }
    
    public void filterByCharacterCount() throws Exception {
           int number = Integer.parseInt(characterCountTextField.getText());
            // Proceed only if number user gave is not bigger than word length
            if (number <= solver.getWord().length()) {
                listView.getItems().clear();
                listView.getItems().addAll(
                        allPossibleWords.stream().filter(n -> n.length() == number)
                                .collect(Collectors.toCollection(ArrayList::new)));
            }
            characterCountTextField.setText("");
            listViewScrollBarReset();
    }

}
