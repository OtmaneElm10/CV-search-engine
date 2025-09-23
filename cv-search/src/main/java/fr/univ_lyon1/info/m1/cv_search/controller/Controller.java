package fr.univ_lyon1.info.m1.cv_search.controller;
import fr.univ_lyon1.info.m1.cv_search.model.AllAboveThresholdStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.AverageAboveThresholdStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.SelectionStrategy;

import java.util.ArrayList;
import java.util.List;


/**
 * Controller Class used to connect view-model.
 */


public class Controller {

    private final ApplicantList applicantList;
    private SelectionStrategy strategy;
    private final List<String> requiredskills = new ArrayList<>();

    /**
     * Constructor.
     * @param applicantList
     */
    public Controller(final ApplicantList applicantList) {
        this.applicantList = applicantList;
    }



   //----Skills----

   /**
    * Add skill to the list of required skills and notify all observers.
    * @param skill skill 
    */
    public void addSkill(final String skill) {
        requiredskills.add(skill);
        applicantList.notifyObservers();   
    }


    /**
     * remove skill from the list of required skills and notify observers.
     * @param skill skill
     */
    public void removeSkill(final String skill) {
        requiredskills.remove(skill);
        applicantList.notifyObservers();
    }




   //----Strategy----


    /**
     * Choose the strategy and notify observers.
     * @param strategy strategy picked by the user
     */
    public void setStrategy(final SelectionStrategy strategy) {
        this.strategy = strategy;
        applicantList.notifyObservers();
    }


    /**
     * Set strategy choice and send it to the model.
     * @param label strategy choice 
     */
    public void setStrategyFromLabel(final String label) {
        switch (label) {
            case "ALL >= 50" -> setStrategy(new AllAboveThresholdStrategy(50));
            case "ALL >= 60" -> setStrategy(new AllAboveThresholdStrategy(60));
            case "AVERAGE >= 50" -> setStrategy(new AverageAboveThresholdStrategy(50));
            default -> setStrategy(null);
        }
    }
   //----Search----


    /**
     * notify observers when search button pressed.
     */
    public void search() {
        applicantList.notifyObservers();
    }

   //----Getters----

    public List<String> getRequiredSkills() {
        return requiredskills;
    }

    public SelectionStrategy getStrategy() {
        return strategy;
    }

    public String getStrategyLabel() {
        return strategy != null ? strategy.getLabel() : "Aucune stratégie";

    }



  //----Selection----

    public List<Applicant> getSelectedApplicants() {
        return applicantList.getSelectedapplicants(strategy, requiredskills);
    }

   


}



