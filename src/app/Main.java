package app;

import java.util.*;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;

public class Main extends Application {

    private ShowResultsWindow showResultsWindow;
    private InsertWordsWindow insertWordsWindow;
    private ExpertModeWindow expertModeWindow;
    private Parent insertWordsWindowParent;
    private Stage primary;
    private Scene showResultsScene;
    private ExpertSolver expert;

    @Override
    public void start(Stage primary) {

        this.primary = primary;
        insertWordsWindow = new InsertWordsWindow();
        showResultsWindow = new ShowResultsWindow();
        expertModeWindow = new ExpertModeWindow(showResultsWindow.getSolver());
        insertWordsWindowParent = insertWordsWindow.getParent();
        expert = new ExpertSolver(expertModeWindow);

        Scene insertWordScene = new Scene(insertWordsWindowParent, 300, 250);
        showResultsScene = new Scene(showResultsWindow.getParent(), 300, 250);
        Scene expertModeScene = new Scene (expertModeWindow.getParent(), 300, 250);
        

        // Adding listeners
        insertWordsWindow.getButton().setOnAction(event -> {
            tryToSolve();
        });

        showResultsWindow.getBackButton().setOnAction(event -> {
            primary.setScene(insertWordScene);
            insertWordsWindow.changeView();
        });

        showResultsWindow.getCharacterCountTextField().setOnAction(event -> {
            filterByCharacterCount();
            showResultsWindow.listViewScrollBarReset();
        });
        
        showResultsWindow.getExpertButton().setOnAction(event -> {
            expertModeWindow.changeView();
            primary.setScene(expertModeScene);
        });
        
        expertModeWindow.getBackToNormalModeButton().setOnAction(event -> {
            primary.setScene(showResultsScene);
        });
        
        expertModeWindow.getSearchButton().setOnAction(event -> {
            handleExpertSearching(expertModeWindow.getWordLengthComboBox().getSelectionModel().getSelectedIndex());
        });

        insertWordsWindowParent.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    tryToSolve();
                }
            }
        });

        primary.setScene(insertWordScene);
        primary.setTitle("Word Solver");
        primary.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void tryToSolve() {
        // First checking that textfield is not empty
        String solvableWord = insertWordsWindow.getSolvableTextField().getText();
        if (!solvableWord.isEmpty()) {
            // then checking if word contains only characters
            for (int i = 0; i < solvableWord.length(); i++) {
                if (!Character.isLetter(solvableWord.charAt(i))) {
                   new Alert(Alert.AlertType.ERROR, "Please enter only letters!").showAndWait(); 
                   insertWordsWindow.getSolvableTextField().setText("");
                    return;
                }
            }
            showResultsWindow.changeView(solvableWord);
            primary.setScene(showResultsScene);
        } else {
            new Alert(Alert.AlertType.ERROR,"Please enter some characters!").showAndWait();
        }
    }

    private void filterByCharacterCount() {
        try {
            int luku = Integer.parseInt(showResultsWindow.getCharacterCountTextField().getText());
            // Proceed only if number user gave is not bigger than word length
            if (luku <= showResultsWindow.getSolver().getWord().length()) {
                showResultsWindow.getListView().getItems().clear();
                showResultsWindow.getListView().getItems().addAll(
                        showResultsWindow.getAllPossibleWords().stream().filter(n -> n.length() == luku)
                                .collect(Collectors.toCollection(ArrayList::new)));
            }
            showResultsWindow.getCharacterCountTextField().setText("");
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid number!").showAndWait();
        }
    }

    private void handleExpertSearching(int index) {
        List<String> list = expert.getAllPossibleWordsByLength(index);
        expertModeWindow.setListViewItems(list);
    }

}
