package fr.univ_lyon1.info.m1.cv_search.model;

import fr.univ_lyon1.info.m1.cv_search.Dao.ApplicantDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Wrapper around {@link List<Applicant>} implementing the observer pattern.
 */
public class ApplicantList implements Iterable<Applicant>, Observable {


    private final ApplicantDao applicantDao;
                                                       
    private List<Observer> observers = new ArrayList<Observer>();

    
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

    /**
     *
     * @param skills list of selected skills
     * @return median value
     */
    public double calculateMedianOfAverages(final List<String> skills) {
        if (skills == null || skills.isEmpty()) {
            return 0.0;
        }

        List<Double> averages = new ArrayList<>();

        // Average
        for (Applicant applicant : this) {
            double sum = 0.0;
            int count = 0;

            for (String skill : skills) {
                sum += applicant.getSkill(skill);
                count++;
            }

            if (count > 0) {
                double average = sum / count;
                averages.add(average);
            }
        }

        // if one of there haven't an average
        if (averages.isEmpty()) {
            return 0.0;
        }

        // short the average
        averages.sort(Double::compareTo);

        // median calculating
        int size = averages.size();
        if (size % 2 == 0) {
            // Even number
            double mid1 = averages.get(size / 2 - 1);
            double mid2 = averages.get(size / 2);
            return (mid1 + mid2) / 2.0;
        } else {
            // odd number
            return averages.get(size / 2);
        }
    }
}
