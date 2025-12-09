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


    
    /**
     * Get the list of red flags for the applicant.
     * @return the list of red flags
     */
    public List<String> getRedFlags() {

        List<String> flags = new ArrayList<>();
        List<Experience> exps = getExperience();

        if (exps == null || exps.isEmpty()) {
            flags.add("Pas d'expérience professionnelle");
            return flags;
        }

        // Sort the experience by start date
        exps.sort(Comparator.comparing(Experience::getStart));

        for (int i = 0; i < exps.size(); i++) {
            
            Experience exp = exps.get(i);
            int duration = exp.getDuree(); 

            // short experience flag
            if (duration <= 1) {
                flags.add("Expérience courte : " + exp.getEntreprise() 
                      + " (" + duration + " an" + (duration > 1 ? "s" : "") + ")");
            }

        
            if (i + 1 < exps.size()) {
                Experience next = exps.get(i + 1);

                int gap = next.getStart() - exp.getFin();

                //gap in the CV
                if (gap >= 2) {
                    flags.add("Trou dans le CV (" 
                          + exp.getFin() + " → " + next.getStart() + ")");
                }
            }
        }
    
        return flags;

    }


}
