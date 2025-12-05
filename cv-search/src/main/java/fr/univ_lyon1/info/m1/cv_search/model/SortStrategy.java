package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.List;

/**
 * interface defining a shorting strategy for applicant.
 */
public interface SortStrategy {
    List<Applicant> sort(List<Applicant> applicants);
}
