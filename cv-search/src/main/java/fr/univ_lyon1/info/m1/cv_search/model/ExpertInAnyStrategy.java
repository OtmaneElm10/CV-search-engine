package fr.univ_lyon1.info.m1.cv_search.model;
import java.util.ArrayList;
import java.util.List;

/**
 * this Stgrategy has been creating to diplay all candidates.
 * who is an expert in on or plus selected skills.
 * that mean 70% or more in one of selected skills.
 */
public class ExpertInAnyStrategy implements SelectionStrategy {

    private int threshold;

    /**
     *
     * @param threshold Expert >= threshold.
     */
    public ExpertInAnyStrategy(final int threshold) {
        this.threshold = threshold;
    }

    /**
     *
     * @param applicant the candidate being evaluated
     * @param selectedSkills the list of required skill
     * @return this candidate has an expert level of one or more selectedskills
     */
    @Override
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
    @Override
    public String getLabel() {
        return "EXPERT >= " + threshold;

    }


    public int getThreshold() {
        return threshold;

    }


    @Override
    public List<String> getHighlightSkills(final Applicant a, final List<String> requiredSkills) {
        List<String> expertList = new ArrayList<>();
        for (String skill:requiredSkills) {
            Integer value = a.getSkills().get(skill);
            if (value != null && value >= threshold) {
                expertList.add(skill);
            } 
        }

        return expertList;
    }

}

