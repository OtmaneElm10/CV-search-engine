package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Wrapper around {@link List<Applicant>} implementing the observer pattern.
 */
public class ApplicantList implements Iterable<Applicant>, Observable {


    private List<Applicant> list = new ArrayList<Applicant>();
    private List<Observer> observers = new ArrayList<Observer>();

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
        list.add(a);
    }

    /**
     * Get the number of applicants in the list.
     */
    public int size() {
        return list.size();
    }
   
    /**
    * Returns an iterator over the applicants in the list.
     *
     * @return an iterator for the applicant list
    */
    @Override
    public Iterator<Applicant> iterator() {
        return list.iterator();
    }

    /** Clear the list of applicants. */
    public void clear() {
        list.clear();
    }

    /** 
     * Sets the content of the applicant list. 
     * @param list list of applicant 
    */
    public void setList(final ApplicantList list) {
        this.list = list.list;
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
}
