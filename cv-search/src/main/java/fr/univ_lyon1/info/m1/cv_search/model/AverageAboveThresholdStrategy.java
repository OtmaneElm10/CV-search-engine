package fr.univ_lyon1.info.m1.cv_search.model;
import java.util.List;



/**
 * Strategy used to know the avarage.
 */

public class AverageAboveThresholdStrategy implements SelectionStrategy {
    private int threshold;

    /**
     * Average >= threshold.
     * @param threshold limit
     */
    public AverageAboveThresholdStrategy(final int threshold) {
        this.threshold = threshold;
    }
    

    /**
     * Check if the applicant is selected according to the average strategy.
     *
    * @param applicant the candidate being evaluated
     * @param requiredSkills the list of required skills
     * @return true if the average skill level is greater than or equal to the threshold
    */
    @Override
    public boolean isSelected(final Applicant applicant, final List<String> requiredSkills) {
        int total = 0;
        int count = 0;

        for (String skill : requiredSkills) {
            total += applicant.getSkill(skill);
            count++;
        }

        if (count == 0) {
            return false;
        }

        double average = (double) total / count;
        return average >= threshold;
    }

    /**
     * Return label strategy choice.
     */
    public String getLabel() {
        return "AVERAGE >= " + threshold;

    }

    
    public int getThreshold() {
        return threshold;

    }

}
