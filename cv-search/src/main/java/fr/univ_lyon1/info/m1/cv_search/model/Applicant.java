package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Applicant, i.e. person having a name and a list of (skill, score) pairs.
 */
public class Applicant {
    private Map<String, Integer> skills = new HashMap<>();
    private String name;

    /**
     * Get the score for a given skill.
     */
    public int getSkill(final String skillName) {
        return skills.getOrDefault(skillName, 0);
    }

    /**
     * Assign score {@param value} to skill {@param skillName} for the current applicant.
     */
    public void setSkill(final String skillName, final int value) {
        skills.put(skillName, value);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

   
    public double getAverage(List<String> skills) {
    if (skills == null || skills.isEmpty()) {
        return 0.0;
    }

    double total = 0;
    int count = 0;

    for (String skill : skills) {
        total += getSkill(skill); // tu dois déjà avoir getSkill(String)
        count++;
    }

    return (count > 0) ? total / count : 0.0;
}

}
