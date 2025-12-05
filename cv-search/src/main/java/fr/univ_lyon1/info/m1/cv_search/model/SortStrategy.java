package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.List;

/**
 * Strategy for sorting applicants.
 */

public interface SortStrategy {
    /**
     * Sorts a list of applicants.
     * @param applicants
     * @return
     */
    List<Applicant> sort(List<Applicant> applicants);
}
