package fr.univ_lyon1.info.m1.cv_search.model;

import fr.univ_lyon1.info.m1.cv_search.Dao.ApplicantDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Wrapper around {@link List<Applicant>} implementing the observer pattern.
 */
public class ApplicantList implements Iterable<Applicant>, Observable {


    private final ApplicantDao applicantDao;
                                                       
    private List<Observer> observers = new ArrayList<Observer>();

    private static final int RARE_THRESHOLD = 2; 
    //we consider a skill rare if it is used less or equal to 2 times

    
    /**
     * Constructor.
     * @param applicantDao the dao to use to get the list of applicants
     */
    public ApplicantList(final ApplicantDao applicantDao) {
        this.applicantDao = applicantDao;
    }
    
    
    
    /**
     * Add new observer to the list of observers.
     */
    @Override
    public void addObserver(final Observer o) {
        observers.add(o);
    }

    /**
     * delete observer from the list of observers.
     */
    @Override
    public void deleteObserver(final Observer o) {
        observers.remove(o);
    }

    /**
     * Notify all observers when there is a new change.
     */
    @Override
    public void notifyObservers() {
        for (Observer o:observers) {
            o.update();
        }
    }


    void add(final Applicant a) {
        applicantDao.add(a);
    }

    /**
     * Get the number of applicants in the list.
     */
    public int size() {
        return applicantDao.size();
    }
   
    /**
     * Get an iterator over the list of applicants.
     */
    public Iterator<Applicant> iterator() {
        return applicantDao.findAll().iterator();
    }

    /** Clear the list of applicants. */
    public void clear() {
        applicantDao.deleteAll();
    }

    /** 
     * Sets the content of the applicant list. 
     * @param list list of applicant 
    */
    public void setList(final ApplicantList list) {
        applicantDao.deleteAll();
        for (Applicant a: list) {
            add(a);
        }
    }


    /**
     * Method used to know if a applicant is selected or not.
     * @param strategy type of strategy
     * @param requiredskills skills entered by the user 
     * @return list of selected applicants
     */
    public List<Applicant> getSelectedapplicants(final SelectionStrategy strategy, 
        final List<String> requiredskills) {

        List<Applicant> selected = new ArrayList<>();

        if (strategy == null) {
            return selected;
        }
        System.out.println("Nb candidats dans ApplicantList = " + this.size()); 
        for (Applicant a: this) {
            if (strategy.isSelected(a, requiredskills)) {
                selected.add(a);
            }
        }

        return selected;
    }


    //skills rarety

    
    
    /**
     * Compute the frequency of each skill in the list of applicants.
     * @return
     */
    public Map<String, Integer> computeSkillsFrequency() {
        Map<String, Integer> skillsFrequency = new HashMap<>();
        for (Applicant a : this) {
            for (String skill : a.getSkills().keySet()) {
                skillsFrequency.merge(skill.toLowerCase(), 1, Integer::sum);
            }       
        }

        return skillsFrequency;
    }


    /**
     * Get the list of rare skills for a given applicant.
     * @param a the applicant
     * @return the list of rare skills
     */
    public List<String> getRareSkills(final Applicant a) {
        Map<String, Integer> freq = computeSkillsFrequency();
        List<String> rareSkills = new ArrayList<>();
        
        for (String skill : a.getSkills().keySet()) {
            if (freq.getOrDefault(skill.toLowerCase(), 0) <= RARE_THRESHOLD) {
                rareSkills.add(skill);
            }
        } 

        return rareSkills;
    }

}
