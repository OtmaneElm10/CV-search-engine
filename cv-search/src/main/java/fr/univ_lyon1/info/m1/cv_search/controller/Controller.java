package fr.univ_lyon1.info.m1.cv_search.controller;
import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.Experience;
import fr.univ_lyon1.info.m1.cv_search.model.Observer;
import fr.univ_lyon1.info.m1.cv_search.model.SelectionStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.SortByExperienceDesc;
import fr.univ_lyon1.info.m1.cv_search.model.SortStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.StrategyFactory;
import fr.univ_lyon1.info.m1.cv_search.model.StrategyType;
import fr.univ_lyon1.info.m1.cv_search.view.ApplicantviewData;


/**
 * Controller Class used to connect view-model.
 */


public class Controller {

    private final ApplicantList applicantList;
    private SelectionStrategy strategy;
    private StrategyType strategyType = StrategyType.ALL_50;
    private final List<String> requiredskills = new ArrayList<>();
    private SortStrategy sortStrategy;


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


  /**
   * Get list of selected applicants.
   * @return list of selected applicants
   */  

    public List<Applicant> getSelectedApplicants() {
        List<Applicant> selected = applicantList.getSelectedapplicants(strategy, requiredskills);
        if (sortStrategy != null) {
            return sortStrategy.sort(selected);
        }
        return selected;
    }



    /**
     * Set sort strategy.
     * @param sortStrategy sort strategy
     */
    public void setSortStrategy(final SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
        applicantList.notifyObservers();
    }



    
    /**
     * Register view.
     * @param view current view
     */
    public void registerView(final Observer view) {
        applicantList.addObserver(view);
    }



    
    /**
     * Apply sort strategy.
     * @param shortType sort strategy

     */
    public void applySortStrategy(final String shortType) {
        if ("Années d'expérience (décroissant)".equals(shortType)) {
            this.sortStrategy = new SortByExperienceDesc();
        } else {
            this.sortStrategy = null;
        }
        applicantList.notifyObservers();
    }



    // ----- Experience utils ----

    
    /**
     * Get list of experience description (complete specification).
     * @param applicant applicant 
     * @return string of detailed experience
     */
    public List<String> getExperienceDescriptions(final Applicant applicant) {
        List<String> lines = new ArrayList<>();

        for (Experience exp : applicant.getExperience()) {
            String line = exp.getEntreprise()
                + " : " + exp.getStart() + "-" + exp.getFin()
                + " (" + exp.getDuree() + " ans)"
                + " | " + String.join(", ", exp.getKeywords());
            lines.add(line);
        }

        return lines;
    }



    
    /**
     * construction of applicants dtos.
     * @return result dto
     */
   
    public List<ApplicantviewData> getApplicantViewDataList() {
        List<ApplicantviewData> result = new ArrayList<>();

        for (Applicant a : getSelectedApplicants()) {
            double avg = a.getAverage(getRequiredSkills());
            int totalExp = a.getTotalExperience();
            List<String> experts = getExpertSkills(a);
            List<String> expLines = getExperienceDescriptions(a);

            result.add(new ApplicantviewData(
                a.getName(),
                avg,
                totalExp,
                experts,
                expLines
            ));
        }

        return result;
    }

}


