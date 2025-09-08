package fr.univ_lyon1.info.m1.cv_search.model;
import java.util.List;

public class AverageAboveThresholdStrategy implements SelectionStrategy{
    private int threshold;


    public AverageAboveThresholdStrategy (int threshold) {
        this.threshold = threshold;
    }
    


    @Override

    public boolean isSelected (Applicant applicant, java.util.List<String> requiredSkills) {
        int total = 0;
        int count = 0;

        for (String skill : requiredSkills) {
            total += applicant.getSkill(skill);
            count++
        }

        if (count == 0) {
            return false ;
        }

        double average = (double)total /count ;
        return average >= threshold;
    }
}
