package fr.univ_lyon1.info.m1.cv_search.view;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Vue représentant une carte simple pour un candidat.
 */
public class ApplicantCard extends VBox {

    /**
     * Card for an applicant.
     * @param a
     * @param controller
     */
    public ApplicantCard(final Applicant a, final Controller controller) {
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
        Label nameLabel = new Label(a.getName());
        nameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Avg of selected skills
        double avg = a.getAverage(controller.getRequiredSkills());
        Label avgLabel = new Label(
            "Moyenne des skills sélectionnées : " + String.format("%.2f", avg));

        // total experience
        Label expLabel = new Label("Expérience totale : " + a.getTotalExperience() + " ans");

        // skills where the applicant is considered expert
        List<String> expertSkills = controller.getExpertSkills(a);
        if (!expertSkills.isEmpty()) {
            Label expertLabel = new Label("Expert en : " + String.join(", ", expertSkills));
            getChildren().addAll(nameLabel, avgLabel, expLabel, expertLabel);
        } else {
            getChildren().addAll(nameLabel, avgLabel, expLabel);
        }
    }
}
