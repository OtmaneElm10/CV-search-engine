package fr.univ_lyon1.info.m1.cv_search.model;
import java.util.List;

/**
 * Strategy use to know who is above the median
 */

public class MedianAboveThresholdStrategy implements SelectionStrategy{

    /**
     * @param threshold represent the median
     */
    private int threshold;


    public MedianAboveThresholdStrategy(final int threshold) {
        this.threshold = threshold;
    }


    /**
     *
     * @param applicant
     * @param requiredSkills
     * @return
     */
    public boolean isSelect (final Applicant applicant, final List<String> requiredSkills){

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

}