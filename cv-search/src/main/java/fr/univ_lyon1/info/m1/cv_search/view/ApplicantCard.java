package fr.univ_lyon1.info.m1.cv_search.view;

import java.util.List;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Vue représentant une carte simple pour un candidat.
 */
public class ApplicantCard extends VBox {

    /**
     * Card for an applicant.
     * @param data applicant dto
     * @param controller controller
     */
    public ApplicantCard(final ApplicantviewData data, final Controller controller) {
        setSpacing(5);
        setStyle("""
            -fx-padding: 10;
            -fx-background-color: #f8f8f8;
            -fx-border-color: #ccc;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 4, 0, 0, 1);
            """);

        // Name of the applicant
        Label nameLabel = new Label(data.getName());
        nameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Avg of selected skills
        Label avgLabel = new Label(
            "Moyenne des skills sélectionnées : " + String.format("%.2f", data.getAverage()));

        // total experience
        Label expLabel = new Label("Expérience totale : " + data.getTotalExperience() + " ans");

        // skills where the applicant is considered expert
        List<String> expertSkills = data.getExpertSkills();
        if (!expertSkills.isEmpty()) {
            Label expertLabel = new Label("Expert en : " + String.join(", ", expertSkills));
            getChildren().addAll(nameLabel, avgLabel, expLabel, expertLabel);
        } else {
            getChildren().addAll(nameLabel, avgLabel, expLabel);
        }
    }
}
