package fr.univ_lyon1.info.m1.cv_search.view;



/**
 * Decorator used to add color to the card depending on the experience.
 */
public class ExperienceColorDecorator implements CardDecorator {

    @Override
    public void decorate(final ApplicantCard card, final ApplicantviewData a) {
        int years = a.getTotalExperience();
        String extraStyle;

        if (years >= 10) { // we choose a if/else because to know if an applicant is senior,
            // intermediate or junior, we can simply consider a applicant senior
            //  when he has more than 10 years of experience, and below 5 years of experience
            //he is junior, and between the two is intermediate --> only 3 cases possibles

            extraStyle = "-fx-background-color: #d4edda;"; // senior
        } else if (years >= 5) {
            extraStyle = "-fx-background-color: #fff3cd;"; // intermediate
        } else {
            extraStyle = "-fx-background-color: #f8d7da;"; // junior
        }

        card.setStyle(card.getStyle() + extraStyle);
    }
}

