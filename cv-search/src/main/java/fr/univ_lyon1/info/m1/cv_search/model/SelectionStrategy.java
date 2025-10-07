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
}
