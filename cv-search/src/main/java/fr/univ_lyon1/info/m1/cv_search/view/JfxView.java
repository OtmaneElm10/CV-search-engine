package fr.univ_lyon1.info.m1.cv_search.view;

import java.io.File;
import java.util.List;

import fr.univ_lyon1.info.m1.cv_search.model.AllAboveThresholdStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.AverageAboveThresholdStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.SelectionStrategy;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Main view of the application, implemented using JavaFX.
 */
public class JfxView {
    private HBox searchSkillsBox;
    private VBox resultBox;
    private ComboBox<ComboItem> comboBox; 

    /**
     * Create the main view of the application.
     */
    public JfxView(final Stage stage, final int width, final int height) {
        // Name of window
        stage.setTitle("Search for CV");

        VBox root = new VBox();

        Node newSkillBox = createNewSkillWidget();
        root.getChildren().add(newSkillBox);

        Node searchSkillsBox = createCurrentSearchSkillsWidget();
        root.getChildren().add(searchSkillsBox);


        Node search = createSearchWidget();
        root.getChildren().add(search);

        Node resultBox = createResultsWidget();
        root.getChildren().add(resultBox);

        Node strategywidget = StrategyChoiceStrategyWidget();
        root.getChildren().add(strategywidget);

        // Everything's ready: add it to the scene and display it
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public enum StrategyType {
        ALL, Average
    }

    public static class ComboItem {
    private String text;
    private SelectionStrategy strategy;

    public ComboItem(String text, SelectionStrategy strategy) {
        this.text = text;
        this.strategy = strategy;
    }

    public SelectionStrategy getStrategy() {
        return strategy;
    }

    @Override
    public String toString() {
        return text; 
    }
}



    /**
     * Create the text field to enter a new skill.
     */
    private Node createNewSkillWidget() {
        HBox newSkillBox = new HBox();
        Label labelSkill = new Label("Skill:");
        TextField textField = new TextField();
        Button submitButton = new Button("Add skill");
        newSkillBox.getChildren().addAll(labelSkill, textField, submitButton);
        newSkillBox.setSpacing(10);

        EventHandler<ActionEvent> skillHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                String text = textField.getText().strip();
                if (text.equals("")) {
                    return; // Do nothing
                }

                Button skillBtn = new Button(text);
                searchSkillsBox.getChildren().add(skillBtn);
                skillBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent event) {
                        searchSkillsBox.getChildren().remove(skillBtn);
                    }
                });

                textField.setText("");
                textField.requestFocus();
            }
        };
        submitButton.setOnAction(skillHandler);
        textField.setOnAction(skillHandler);
        return newSkillBox;
    }

    /**
     * Create the widget showing the list of applicants.
     */
    private Node createResultsWidget() {
        resultBox = new VBox();
        return resultBox;
    }

    /**
     * Create the widget used to trigger the search.
     */
   private Node createSearchWidget() {
    Button search = new Button("Search");
    search.setOnAction(event -> {
        ApplicantList listApplicants = new ApplicantListBuilder(new File(".")).build();
        resultBox.getChildren().clear();

        // get the strategy of comboBox
        SelectionStrategy strategy = comboBox.getValue().getStrategy();

        
        List<String> requiredSkills = searchSkillsBox.getChildren().stream()
                .map(node -> ((Button) node).getText())
                .toList();

        // apply strategy for every person
        for (Applicant a : listApplicants) {
            if (strategy.isSelected(a, requiredSkills)) {
                String text = a.getName() + " - Moyenne : " + 
                              String.format("%.2f", a.getAverage(requiredSkills));
                resultBox.getChildren().add(new Label(text));
            }
        }
    });
    return search;
}



    /**
     * Create the widget showing the list of skills currently searched
     * for.
     */
    private Node createCurrentSearchSkillsWidget() {
        searchSkillsBox = new HBox();
        return searchSkillsBox;
    }



    /**
     * Create the widget showing the strategy used by the user
     * 
     */

    private Node StrategyChoiceStrategyWidget() {
    Label strategyLabel = new Label("Strategy : ");
    comboBox = new ComboBox<>();
    comboBox.getItems().addAll( 
        new ComboItem("ALL >= 50", new AllAboveThresholdStrategy(50)),
        new ComboItem("ALL >= 60", new AllAboveThresholdStrategy(60)),
        new ComboItem("Average >= 50", new AverageAboveThresholdStrategy(50))
    );

    comboBox.setValue(comboBox.getItems().get(0)); 

    HBox box = new HBox(strategyLabel, comboBox);
    box.setSpacing(10);

    return box;
}



}
