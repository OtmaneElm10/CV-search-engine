package fr.univ_lyon1.info.m1.cv_search.view;

import javafx.scene.control.Label;


/**
 * Decorator to show red flags of an applicant in the card.
 */
public class RedFlagDecorator implements CardDecorator {

    @Override
    public void decorate(final ApplicantCard card, final ApplicantviewData data) {

        if (data.getRedFlags().isEmpty()) {
            return; // nothing to show
        }

        Label title = new Label("! Red Flags détectés :");
        title.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

        card.getChildren().add(title);

        for (String flag : data.getRedFlags()) {
            Label flagLabel = new Label("• " + flag);
            flagLabel.setStyle("-fx-text-fill: darkred;");
            card.getChildren().add(flagLabel);
        }
    }
}

