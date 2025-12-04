package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.Comparator;
import java.util.List;

public class SortByExperienceDesc implements SortStrategy {

    @Override
    public List<Applicant> sort(List<Applicant> applicants) {
        return applicants.stream()
                .sorted(Comparator.comparingInt(Applicant::getTotalExperience).reversed())
                .toList();
    }
}
