package fr.univ_lyon1.info.m1.cv_search.controller;
import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.SelectionStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.StrategyFactory;
import fr.univ_lyon1.info.m1.cv_search.model.StrategyType;
import fr.univ_lyon1.info.m1.cv_search.model.SortStrategy;


import java.util.ArrayList;
import java.util.List;


/**
 * Controller Class used to connect view-model.
 */


public class Controller {

    private final ApplicantList applicantList;
    private SelectionStrategy strategy;
    private StrategyType strategyType = StrategyType.ALL_50;
    private final List<String> requiredskills = new ArrayList<>();
    private SortStrategy sortStrategy;   // nouvelle variable


    /**
     * Constructor.
     * @param applicantList
     */
    public Controller(final ApplicantList applicantList) {
        this.applicantList = applicantList;
        //strategy initialisation
        this.strategy = StrategyFactory.createStrategy(strategyType);
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



    public List<String> getRequiredSkills() {
        return requiredskills;
    }
    
    /**
     * Return list of skill where the applicant is considered like an expert.
     * @param a applicant
     * @return list of skills
     */
    public List<String> getExpertSkills(final Applicant a) {
        return strategy.getHighlightSkills(a, requiredskills);
    }


    
   //----Strategy----


    /**
     * Choose the strategy and notify observers.
     * @param type strategy type picked by the user
     */
    public void setStrategy(final StrategyType type) {
        this.strategyType = type;
        this.strategy = StrategyFactory.createStrategy(type);
        applicantList.notifyObservers();
    }


    /**
     * return selected strategy.
     * @return current strategy
     */
    public SelectionStrategy geStrategy() {
        return strategy;
    }


    /**
     * get Label of the strategy.
     * @return label
     */
    public String getStrategyLabel() {
        return strategyType.getLabel();
    }



    /**
     * get type of strategy.
     * @return type of strategy
     */
    public StrategyType getStrategyType() {
        return strategyType;
    }


   //----Search----


    /**
     * notify observers when search button pressed.
     */
    public void search() {
        applicantList.notifyObservers();
    }

   

  //----Selection----

    public List<Applicant> getSelectedApplicants() {
        List<Applicant> selected = applicantList.getSelectedapplicants(strategy, requiredskills);

        // 2. Tri si demandé
        if (sortStrategy != null) {
            return sortStrategy.sort(selected);
        }

        return selected;
    }

    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
        applicantList.notifyObservers();  // pour que la vue se mette à jour
    }




}



