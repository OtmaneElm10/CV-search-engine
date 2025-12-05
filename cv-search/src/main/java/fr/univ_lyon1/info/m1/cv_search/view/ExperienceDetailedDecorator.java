package fr.univ_lyon1.info.m1.cv_search.view;

import java.util.List;
import javafx.scene.control.Label;


/**
 * Decorator to add experience details to the card.
 */
public class ExperienceDetailedDecorator implements CardDecorator {

    @Override
    public void decorate(final ApplicantCard card, final ApplicantviewData a) {
        List<String> lines = a.getExperienceLines();

        for (String line : lines) {
            card.getChildren().add(new Label(line));
        }
    }
}

