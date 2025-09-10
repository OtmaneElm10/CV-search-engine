package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.List;

public interface SelectionStrategy {
    /**
 * Check if a candidate matches the required skills.
 *
 * @param applicant the candidate
 * @param skills the list of required skills
 * @return true if the candidate is selected according to the strategy
 */
    boolean isSelected(Applicant applicant, List<String> skills);

    /**
     * textual rapresentation
     */
    @Override
    String toString();
}
