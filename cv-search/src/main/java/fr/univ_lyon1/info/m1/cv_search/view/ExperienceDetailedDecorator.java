package fr.univ_lyon1.info.m1.cv_search.view;

import java.util.List;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import javafx.scene.control.Label;


/**
 * Decorator to add experience details to the card.
 */
public class ExperienceDetailedDecorator implements CardDecorator {

    @Override
    public void decorate(final ApplicantCard card, final Applicant applicant,
         final Controller controller) {
        List<String> lines = controller.getExperienceDescriptions(applicant);

        for (String line : lines) {
            card.getChildren().add(new Label(line));
        }
    }
}

