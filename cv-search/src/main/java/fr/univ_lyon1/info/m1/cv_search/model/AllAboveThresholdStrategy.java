package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.List;

public class AllAboveThresholdStrategy implements SelectionStrategy {
    private int threshold;

    public AllAboveThresholdStrategy(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean isSelected(Applicant applicant, List<String> skills) {
        for (String skill : skills) {
            if (applicant.getSkill(skill) < threshold) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "ALL >= " + threshold;
    }
}
