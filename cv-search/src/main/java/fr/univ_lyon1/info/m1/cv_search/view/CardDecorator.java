package fr.univ_lyon1.info.m1.cv_search.view;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.controller.Controller;



/**
 * Interface used to decorate the card.
 */
public interface CardDecorator {
    
    /**
     * Decorate the card.
     * @param card card
     * @param applicant the applicant card
     * @param controller controller
     */
    void decorate(ApplicantCard card, Applicant applicant, Controller controller);
}
