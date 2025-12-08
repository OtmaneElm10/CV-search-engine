package fr.univ_lyon1.info.m1.cv_search.view;

import javafx.scene.control.Label;

/**
 * Decorator to show rare skills to the card.
 */
public class RareSkillsDecorator implements CardDecorator {


    @Override
    public void decorate(final ApplicantCard card, final
            ApplicantviewData data) {
                
        if (data.getRareSkills().isEmpty()) {
            return;
        }


        Label badge = new Label("⭐ Perle rare : " + String.join(", ", data.getRareSkills()));
        badge.setStyle("""
            -fx-text-fill: #8a2be2;
            -fx-font-weight: bold;
            -fx-background-color: #e8ddff;
            -fx-padding: 3 6;
            -fx-background-radius: 6;
            """);

        
        
        
        card.getChildren().add(badge);



    }
    
}
