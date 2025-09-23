package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.List;


/**
 * Strategy for 'ALL >= x'.
 */
public class AllAboveThresholdStrategy implements SelectionStrategy {
    private int threshold;

    /**
     * Constructor.
     * @param threshold limit
     */
    public AllAboveThresholdStrategy(final int threshold) {
        this.threshold = threshold;
    }

    /**
     * Strategy 'ALL > threshold'.
     *@return true if applicant is selected, false if not selected.
     */
    @Override
    public boolean isSelected(final Applicant applicant, final List<String> skills) {
        for (String skill : skills) {
            if (applicant.getSkill(skill) < threshold) {
                return false;
            }
        }
        return true;
    }

   
    /**
     * return string for comboBox.
     */
    @Override
    public String getLabel() {
        return "ALL >= " + threshold;
    }

 
}
