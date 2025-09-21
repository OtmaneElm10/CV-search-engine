package fr.univ_lyon1.info.m1.cv_search.model;


/**
 * Observable interface for the Observer Pattern.
 */
public interface Observable {
    /**
     * Add a new observer.
     * @param o observer
     */
    void addObserver(Observer o);

    /**
     * Delete observer.
     * @param o observer that we want to delete.
     */
    void deleteObserver(Observer o);

    /**
     * Notify observers of changes.
     */
    void notifyObservers();
}
