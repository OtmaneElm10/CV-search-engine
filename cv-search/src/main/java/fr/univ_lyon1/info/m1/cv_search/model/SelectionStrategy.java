package fr.univ_lyon1.info.m1.cv_search.model;
import java.util.List;



/**
 * Defines a selection strategy for filtering applicants.
 */
public interface SelectionStrategy {
     
    /**
     * Check if a candidate matches the required skills.
     *
     * @param applicant the candidate
     * @param skills the list of required skills
     * @return true if the candidate is selected according to the strategy
     */
    boolean isSelected(Applicant applicant,  List<String> skills);

    /**
     *Return label selected strategy (string).
     * @return string of the selected strategy by the user
     */
    String getLabel();

    /**
     * textual rapresentation.
     */
    @Override
    String toString();

    

    /**
    * Helper used by the EXPERT (>= 70) strategy.
    * <p>
    * By default, it returns an empty list. The EXPERT strategy overrides it to
    * return the list of skills among the required ones for which the applicant
    * has a score >= 70.
    *
    * @param a              the applicant
    * @param requiredSkills the skills selected by the user
    * @return an empty list for non-EXPERT strategies, or the list of skills
    *         where the applicant is considered expert (score >= 70) for EXPERT
    *         strategy
    */
    default List<String> getHighlightSkills(Applicant a, List<String> requiredSkills) {
        return java.util.Collections.emptyList();
    }
}
