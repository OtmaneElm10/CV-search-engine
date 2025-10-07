package fr.univ_lyon1.info.m1.cv_search.model;
import java.util.List;

public class ExpertInAnyStrategy implements SelectionStrategy {

    private int threshold;

    public ExpertInAnyStrategy(final int threshold) {
        this.threshold = threshold;
    }

    /**
     *
     * @param applicant the candidate being evaluated
     * @param selectedSkills the list of required skill
     * @return this candidate has an expert level of one or more selectedskills
     */
    public boolean isSelected(final Applicant applicant, final List<String> selectedSkills) {
        for (String skill : selectedSkills) {
            if (applicant.getSkill(skill) >= threshold) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return label strategy choice.
     */
    public String getLabel() {
        return "EXPERT >= " + threshold;

    }


    public int getThreshold() {
        return threshold;

    }

}