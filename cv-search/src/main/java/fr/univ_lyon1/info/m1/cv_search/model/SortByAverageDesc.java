package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.Comparator;
import java.util.List;


/**
 * Strategy to sort by their average score on selected skills
 */

public class SortByAverageDesc implements SortStrategy {

    private final List<String> skills;

    /**
     * Constructor
     * @param skill is the skills needed to calculate the average of their selected skills
     */
    public SortByAverageDesc(final List<String> skills) {
        this.skills = skills;
    }


    @Override
    public void applySortStrategy(final String sortLabel) {
        return applicants.stream()
                .sorted((a1, a2) -> Double.compare(
                        a2.getAverage(skills),
                        a1.getAverage(skills))
                )
                .toList();
    }
    }
}