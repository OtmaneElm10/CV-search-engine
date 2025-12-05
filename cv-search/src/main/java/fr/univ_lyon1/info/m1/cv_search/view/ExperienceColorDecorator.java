package fr.univ_lyon1.info.m1.cv_search.view;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import fr.univ_lyon1.info.m1.cv_search.model.Applicant;


/**
 * Decorator used to add color to the card depending on the experience.
 */
public class ExperienceColorDecorator implements CardDecorator {

    @Override
    public void decorate(final ApplicantCard card, final Applicant applicant,
         final Controller controller) {
        int years = applicant.getTotalExperience();
        String extraStyle;

        if (years >= 10) {
            extraStyle = "-fx-background-color: #d4edda;"; // senior
        } else if (years >= 5) {
            extraStyle = "-fx-background-color: #fff3cd;"; // intermediate
        } else {
            extraStyle = "-fx-background-color: #f8d7da;"; // junior
        }

        card.setStyle(card.getStyle() + extraStyle);
    }
}

