package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Applicant, i.e. person having a name and a list of (skill, score) pairs.
 */
public class Applicant {
    private Map<String, Integer> skills = new HashMap<>();
    private String name;
    private List<Experience> experience = new ArrayList<>();

    /**
     * Get the score for a given skill.
     */
    public int getSkill(final String skillName) {
        return skills.getOrDefault(skillName, 0);
    }


    public Map<String, Integer> getSkills() {
        return skills;
    }

    /**
     * Assign score.
     * @param skillName the name of the skill 
     * @param value the score to assign.
     */
    public void setSkill(final String skillName, final int value) {
        skills.put(skillName, value);
    }


    /**
     * Get the name of applicant.
     */
    public String getName() {
        return name;
    }


    /**
     *  Set the name of applicant.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
    *Get the average from list of skills.
    * @param skills list of skills
    * @return   the avarage score 
    */
    public double getAverage(final List<String> skills) {
        if (skills == null || skills.isEmpty()) {
            return 0.0;
        }

        double total = 0;
        int count = 0;

        for (String skill : skills) {
            total += getSkill(skill); 
            count++;
        }

        return (count > 0) ? total / count : 0.0;
    }


    // ------------ Experience methods ------------

    /**
     * Get experience.
     * @return experience
     */
    public List<Experience> getExperience() {
        return experience;
    }


    /**
     * Add experience to the list of experiences.
     * @param exp experience
     */
    public void addExperience(final Experience exp) {
        experience.add(exp);
    }

    /**
     * Get the total experience in years.
     * @return the total experience
     */
    public int getTotalExperience() {
        int total = 0;
        for (Experience exp : experience) {
            total += exp.getDuree();
        }
        return total;
    }


    // ------------ Red flag methods ------------

    //We use them here and not in the class Experience because Experience it's an object and don't
    // have access to all the experiences of the applicant. On the other hand, this class Applicant
    // has access to all the experiences of the applicant (List<Experience>),
    //  so we can use them here.



    /**
     * Detect gaps in the experience.
     * @param exps list of experiences
     * @return list of flags
     */
    private List<String> detectGaps(final  List<Experience> exps) {
        List<String> flags = new ArrayList<>();

        for (int i = 0; i < exps.size() - 1; i++) {
            Experience cur = exps.get(i);
            Experience next = exps.get(i + 1);

            int gap = next.getStart() - cur.getFin();

            if (gap >= 2) {
                flags.add("Trou dans le CV : " + cur.getFin() + " -> " + next.getStart());
            }
        }

        return flags;
    }


    
    
    /**
     * Detect consecutive short experiences.
     * @param exps list of experiences
     * @return true if there are consecutive short experiences, false otherwise.
     */
    private boolean hasConsecutiveShortExperiences(final List<Experience> exps) {
        int consecutiveShort = 0;

        for (Experience exp : exps) {
            if (exp.getDuree() <= 1) {
                consecutiveShort++;
            } else {
                consecutiveShort = 0;
            }

            if (consecutiveShort >= 2) {
                return true;
            }
        }

        return false;
    }


    
    /**
     * Global function for detecting red flags. Combine all the previous methods.
     * @return list of flags
     */
    public List<String> getRedFlags() {

        List<String> flags = new ArrayList<>();
        List<Experience> exps = new ArrayList<>(getExperience());

        if (exps.isEmpty()) {
            return flags;
        }

        // Sort experiences by start date
        exps.sort(Comparator.comparing(Experience::getStart));

        //  add gaps
        flags.addAll(detectGaps(exps));

        // 2) add alert if there are consecutive short experiences
        if (hasConsecutiveShortExperiences(exps)) {
            flags.add("Plusieurs expériences courtes consécutives");
        }

        return flags;
    }






    
    


}
