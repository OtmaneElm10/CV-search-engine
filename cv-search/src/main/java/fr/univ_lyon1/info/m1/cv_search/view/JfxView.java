package fr.univ_lyon1.info.m1.cv_search.view;



import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.SelectionStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.Observer;
import fr.univ_lyon1.info.m1.cv_search.model.SortByExperienceDesc;


import java.util.List;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import fr.univ_lyon1.info.m1.cv_search.model.StrategyType;


/**
 * Main view of the application, implemented using JavaFX.
 */
public class JfxView implements Observer {
    private HBox searchSkillsBox;
    private VBox resultBox;
    private ComboBox<StrategyType> comboBox;
    private ApplicantList applicantList;
    private Controller controller;
    private Label strategyLabel;



    /**
     * Create the main view of the application.
     *
     * @param stage  the application stage
     * @param width  the window width
     * @param height the window height
     * @param model link to the model
     * @param controller link to the controller
     */
    public JfxView(final Stage stage, final int width, final int height,
        final ApplicantList model, final Controller controller) {
        this.applicantList = model;
        this.controller = controller;
        applicantList.addObserver(this);


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

        Node strategyWidget = strategyChoiceStrategyWidget();
        root.getChildren().add(strategyWidget);

        Node sortWidget = createSortWidget();
        root.getChildren().add(sortWidget);


        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    
    /**
     * Update results.
     */
    public void update() {
        System.out.println("Vue notifiée, rafraîchissement !");
        refreshSkills();
        refreshResults();
        refreshStrategy();
    }


    /**
     * Item for ComboBox, holding a label and a strategy.
     */
    public static class ComboItem {
        private String text;
        private SelectionStrategy strategy;

        /**
         * Constructor.
         *
         * @param text     text shown in the ComboBox
         * @param strategy the strategy represented
         */
        public ComboItem(final String text, final SelectionStrategy strategy) {
            this.text = text;
            this.strategy = strategy;
        }

        /**
         * Get the strategy associated with this item.
         *
         * @return the selection strategy
         */
        public SelectionStrategy getStrategy() { //elle va sauter de la vue
            return strategy;
        }

        /**
         * Display text in the ComboBox.
         *
         * @return the text
         */
        @Override
        public String toString() {
            return text;
        }
    }

    /**
     * Create the text field to enter a new skill.
     *
     * @return the widget node
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
                    return;
                }

                controller.addSkill(text); //inform the controller
                textField.setText("");
                textField.requestFocus();
                //Suppression in update() 
            }
        };

        submitButton.setOnAction(skillHandler);
        textField.setOnAction(skillHandler);
        return newSkillBox;
    }

    /**
     * Create the widget showing the list of applicants.
     *
     * @return the widget node
     */
    private Node createResultsWidget() {
        resultBox = new VBox();
        return resultBox;
    }

    /**
     * Create the search button and define its behavior.
     *
     * @return the search button
     */
    private Node createSearchWidget() {
        Button search = new Button("Search");
        search.setOnAction(event -> controller.search());
        return search;
    }

    /**
     * Create the widget showing the list of skills currently searched for.
     *
     * @return the widget node
     */
    private Node createCurrentSearchSkillsWidget() {
        searchSkillsBox = new HBox();
        return searchSkillsBox;
    }

    /**
     * Create the widget showing the strategy choice for the user.
     *
     * @return the widget node
     */
    private Node strategyChoiceStrategyWidget() {
        Label label = new Label("Strategy : ");
        strategyLabel = new Label();

        comboBox = new ComboBox<>();

        comboBox.getItems().addAll(StrategyType.values());

        comboBox.setValue(controller.getStrategyType());


        comboBox.setOnAction(event -> {
            StrategyType choice = comboBox.getValue();
            controller.setStrategy(choice);
        });

        HBox box = new HBox(label, strategyLabel, comboBox);
        box.setSpacing(10);

        
        
        return box;



    }
    private Node createSortWidget() {
        Label label = new Label("Tri : ");

        ComboBox<String> sortCombo = new ComboBox<>();
        sortCombo.getItems().add("Aucun tri"); // valeur par défaut
        sortCombo.getItems().add("Années d'expérience (décroissant)");

        sortCombo.setValue("Aucun tri");  // valeur affichée au démarrage

        sortCombo.setOnAction(event -> {
            String choice = sortCombo.getValue();

            if (choice.equals("Années d'expérience (décroissant)")) {
                controller.setSortStrategy(new SortByExperienceDesc());
            } else {
                controller.setSortStrategy(null); // désactive le tri
            }

            controller.search();
        });

        HBox box = new HBox(label, sortCombo);
        box.setSpacing(10);
        return box;
    }



    //  ----Refresh functions, every function -> 1 responsability ----
    
    /**
     * Refresh the results with candidates who satisfy the requirements.
     */
    private void refreshResults() {
        resultBox.getChildren().clear();
        for (Applicant a : controller.getSelectedApplicants()) {
            String text = a.getName()
                    + " - Moyenne skills sélectionnées : "
                    + String.format("%.2f", a.getAverage(controller.getRequiredSkills()))
                    + " | Expérience totale : " + a.getTotalExperience() + " ans";
                
                
            List<String> expertSkills = controller.getExpertSkills(a);
            if (!expertSkills.isEmpty()) {
                text += " (expert en " + String.join(", ", expertSkills) + ")";
            }
               
            resultBox.getChildren().add(new Label(text));
        }
    }

    /**
     * Refresh strategy in the model.
     */
    private void refreshStrategy() {

        //update comboBox status
        comboBox.getSelectionModel().select(controller.getStrategyType());

        //selected strategy
        strategyLabel.setText("Stratégie : " + controller.getStrategyLabel());
    }

    /**
     * Refresh skills by passing in the model.
     */
    private void refreshSkills() {
        searchSkillsBox.getChildren().clear();
        for (String skill : controller.getRequiredSkills()) {

            // box creation to store skill and remove button
            HBox skillBox = new HBox();

            //label for the skill
            Label skillName = new Label(skill);

            //remove button
            Button removeBtn = new Button("X");
            removeBtn.setOnMouseClicked(e -> controller.removeSkill(skill));

            skillBox.setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: black;");

            skillBox.setAlignment(Pos.BASELINE_CENTER);


            //add skill label and remove btn in the skillbox
            skillBox.getChildren().addAll(skillName, removeBtn);

            //add skillbox in the main
            searchSkillsBox.getChildren().add(skillBox);


        }
    }
   
}
