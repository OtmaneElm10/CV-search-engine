package fr.univ_lyon1.info.m1.cv_search.view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import fr.univ_lyon1.info.m1.cv_search.model.Observer;
import fr.univ_lyon1.info.m1.cv_search.model.StrategyType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Main view of the application, implemented using JavaFX.
 */
public class JfxView implements Observer {
    private HBox searchSkillsBox;
    private VBox resultBox;

    private ComboBox<StrategyType> comboBox;

    private CheckBox colorExperienceCheckbox;
    private CheckBox detailExperienceCheckbox;
    private CheckBox rareSkillsCheckbox;
    private CheckBox redFlagsCheckbox;


    private Map<DisplayOption, CheckBox> optionCheckboxes = new HashMap<>();
    private Map<DisplayOption, Supplier<CardDecorator>> optionDecorators = new HashMap<>();
    
    // We use a Supplier (the ::new part) to avoid creating the Decorator object immediately. 
    // It's like having a 'recipe' to build the object later.
    // We do this to ensure two things:
    // 1. **Lazy Creation:** The Decorator is only built if the checkbox is actually selected.
    // 2. **Fresh Instance:** We get a brand new Decorator every time we refresh results, 
    //    which prevents styling bugs when dealing with multiple applicants.


    private Controller controller;
    private Label strategyLabel;



    /**
     * Create the main view of the application.
     *
     * @param stage  the application stage
     * @param width  the window width
     * @param height the window height
     * @param controller link to the controller
     */

    public JfxView(final Stage stage, final int width, final int height,
                   final Controller controller) {


        this.controller = controller;
        controller.registerView(this);

        stage.setTitle("Search for CV");

        VBox root = new VBox();
        root.setSpacing(10);
        root.setStyle("-fx-padding: 10;");

        
        //strategy, new skill
        HBox topSection = new HBox(10);


        Node strategyWidget = strategyChoiceStrategyWidget();
        Node newSkillBox = createNewSkillWidget();

        topSection.getChildren().addAll(strategyWidget, newSkillBox);
        root.getChildren().add(topSection);


        
        //sort, skills
        HBox middleSection = new HBox(20);
        middleSection.setStyle("-fx-padding: 10 0 10 0;");
        middleSection.setAlignment(Pos.CENTER_LEFT);

        Node searchSkillsBox = createCurrentSearchSkillsWidget();
        Node sortWidget = createSortWidget();

        HBox.setHgrow(searchSkillsBox, Priority.ALWAYS);
        
        middleSection.getChildren().addAll(searchSkillsBox, sortWidget);
        root.getChildren().add(middleSection);

        // display options 

        Node displayOptions = createDisplayOptionsWidget();
        root.getChildren().add(displayOptions);

        
        
        //results
        Node resultsContent = createResultsWidget();
        ScrollPane scrollPane = new ScrollPane(resultsContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);
        root.getChildren().add(scrollPane);



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
        resultBox = new VBox(10);
        resultBox.setStyle("-fx-padding: 10;");
        return resultBox;
    }


    /**
     * Create the widget showing the list of skills currently searched for.
     *
     * @return the widget node
     */
    private Node createCurrentSearchSkillsWidget() {
        searchSkillsBox = new HBox();
        searchSkillsBox.setSpacing(10);
        searchSkillsBox.setAlignment(Pos.CENTER_LEFT);
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
        sortCombo.getItems().add("Default"); // valeur par défaut
        sortCombo.getItems().add("Années d'expérience (décroissant)");

        sortCombo.setValue("Default");  // valeur affichée au démarrage

        sortCombo.setOnAction(event -> {
            String choice = sortCombo.getValue();
            controller.applySortStrategy(choice);
        });

        HBox box = new HBox(label, sortCombo);
        box.setSpacing(10);
        return box;
    }


    private Node createDisplayOptionsWidget() {
        HBox box = new HBox(15);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setStyle("-fx-padding: 10 0 10 0;");

        Label label = new Label("Options d'affichage :");

        colorExperienceCheckbox = new CheckBox("Couleur selon expérience");
        detailExperienceCheckbox = new CheckBox("Afficher détails expériences");
        rareSkillsCheckbox = new CheckBox("Afficher perles rares");
        redFlagsCheckbox = new CheckBox("Afficher les red flags");


        // default
        colorExperienceCheckbox.setSelected(true);
        detailExperienceCheckbox.setSelected(true);
        rareSkillsCheckbox.setSelected(true);
        redFlagsCheckbox.setSelected(true);

        // update when changed
        colorExperienceCheckbox.setOnAction(e -> refreshResults());
        detailExperienceCheckbox.setOnAction(e -> refreshResults());
        rareSkillsCheckbox.setOnAction(e -> refreshResults());
        redFlagsCheckbox.setOnAction(e -> refreshResults());

        box.getChildren().addAll(
            label, colorExperienceCheckbox, detailExperienceCheckbox,
            rareSkillsCheckbox, redFlagsCheckbox
        );
        

        // association option/checkbox
        optionCheckboxes.put(DisplayOption.COLOR_EXPERIENCE, colorExperienceCheckbox);
        optionCheckboxes.put(DisplayOption.DETAIL_EXPERIENCE, detailExperienceCheckbox);
        optionCheckboxes.put(DisplayOption.RARE_SKILLS, rareSkillsCheckbox);
        optionCheckboxes.put(DisplayOption.RED_FLAGS, redFlagsCheckbox);

        // association option/decorator
        // when an option is selected, add the corresponding decorator
        // we can create an instance of the decorator because in this case the decorator contains 
        // simply elements for the view, and not logic
        optionDecorators.put(DisplayOption.COLOR_EXPERIENCE, ExperienceColorDecorator::new);
        optionDecorators.put(DisplayOption.DETAIL_EXPERIENCE, ExperienceDetailedDecorator::new);
        optionDecorators.put(DisplayOption.RARE_SKILLS, RareSkillsDecorator::new);
        optionDecorators.put(DisplayOption.RED_FLAGS, RedFlagDecorator::new);


        return box;
    }
    


    //  ----Refresh functions, every function -> 1 responsability ----


    /** 
    //active decorator (based on option choose by user), used by refreshResults().
    */
    private List<CardDecorator> buildActiveDecorators() {
        List<CardDecorator> decorators = new ArrayList<>();

        for (DisplayOption option : optionCheckboxes.keySet()) {
            CheckBox box = optionCheckboxes.get(option);
            if (box.isSelected()) {
                decorators.add(optionDecorators.get(option).get());
            }
        }

        return decorators;
    }



    /**
     * Refresh the results with candidates who satisfy the requirements.
     */

    private void refreshResults() {
        resultBox.getChildren().clear();
        resultBox.setSpacing(10);
        resultBox.setStyle("-fx-padding: 10;");

        // Décorateurs choisis selon les options d’affichage
        List<CardDecorator> decorators = buildActiveDecorators();
        List<ApplicantviewData> viewDataList = controller.getApplicantViewDataList();

        for (ApplicantviewData a : viewDataList) {
            // Card de base
            ApplicantCard card = new ApplicantCard(a, controller);

            // Application des décorateurs actifs
            for (CardDecorator decorator : decorators) {
                decorator.decorate(card, a);
            }

            resultBox.getChildren().add(card);
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
