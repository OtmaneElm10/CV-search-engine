package fr.univ_lyon1.info.m1.cv_search.view;

import java.util.List;


/**
 * Data class to pass to the view (view don't have anymore to call applicant from the model).
 */
public class ApplicantviewData {
    private  String name;
    private  double average;
    private  int totalExperience;
    private  List<String> expertSkills;
    private final List<String> experienceLines;
    private final List<String> rareSkills;
    private final List<String> redFlags;

    /**
     * Constructor.
     * @param name name of the applicant
     * @param average average
     * @param totalExperience total experience
     * @param expertSkills list of expert skills
     */
    public ApplicantviewData(final String name, final double average,
        final int totalExperience, 
        final List<String> expertSkills, final List<String> experienceLines,
            final List<String> rareskills, final List<String> redFlags) {
        this.name = name;
        this.average = average;
        this.totalExperience = totalExperience;
        this.expertSkills = expertSkills;
        this.experienceLines = experienceLines;
        this.rareSkills = rareskills;
        this.redFlags = redFlags;
    }

    public String getName() {
        return name;
    }

    public double getAverage() {
        return average;
    }

    public int getTotalExperience() {
        return totalExperience;
    }

    public List<String> getExpertSkills() {
        return expertSkills;
    }

    public List<String> getExperienceLines() {
        return experienceLines;
    }

    public List<String> getRareSkills() {
        return rareSkills;
    }


    public List<String> getRedFlags() {
        return redFlags;
    }
}
