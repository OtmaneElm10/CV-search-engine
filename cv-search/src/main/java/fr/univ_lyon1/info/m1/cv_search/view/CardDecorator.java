package fr.univ_lyon1.info.m1.cv_search.view;

/**
 * Interface used to decorate the card.
 */
public interface CardDecorator {
    
    /**
     * Decorate the card.
     * @param card card
     * @param a applicant dto
     */
    void decorate(ApplicantCard card, ApplicantviewData a);
}
