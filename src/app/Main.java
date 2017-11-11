package app;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;

public class Main extends Application {

    private ShowResultsWindow showResultsWindow;
    private InsertWordsWindow insertWordsWindow;
    private ExpertModeWindow expertModeWindow;
    private Stage primary;
    private Scene showResultsScene;
    private ExpertSolver expert;

    @Override
    public void start(Stage primary) {

        this.primary = primary;
        insertWordsWindow = new InsertWordsWindow();
        showResultsWindow = new ShowResultsWindow();
        expertModeWindow = new ExpertModeWindow(showResultsWindow.getSolver());
        expert = new ExpertSolver(expertModeWindow);

        showResultsScene = new Scene(showResultsWindow.getParent(), 300, 250);
        Scene insertWordScene = new Scene(insertWordsWindow.getParent(), 300, 250);
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
        });
        
        showResultsWindow.getExpertButton().setOnAction(event -> {
            expertModeWindow.changeView();
            primary.setScene(expertModeScene);
        });
        
        expertModeWindow.getBackToNormalModeButton().setOnAction(event -> {
            primary.setScene(showResultsScene);
        });
        
        expertModeWindow.getSearchButton().setOnAction(event -> {
            expert.handleExpertSearching();
        });

        insertWordScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) 
                tryToSolve();  
        });

        primary.setScene(insertWordScene);
        primary.setTitle("Word Solver");
        primary.setResizable(false);
        primary.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void tryToSolve() {
        try {   
            showResultsWindow.changeView(insertWordsWindow.checkForValidInput());
            primary.setScene(showResultsScene);
        } catch (IllegalArgumentException e) {
          new Alert(Alert.AlertType.ERROR, "Please enter only letters!").showAndWait();
        } catch (NullPointerException e) {
          new Alert(Alert.AlertType.ERROR, "Please enter some characters!").showAndWait();  
        }    
    }

    private void filterByCharacterCount() {
        try {
         showResultsWindow.filterByCharacterCount();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid number!").showAndWait();
        }
    }
}
