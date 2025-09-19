package fr.univ_lyon1.info.m1.cv_search.controller;
import fr.univ_lyon1.info.m1.cv_search.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle.Control;



public class Controller {

   private final ApplicantList applicantList;
   private SelectionStrategy strategy;
   private final List<String> requiredskills = new ArrayList<>();


   public Controller(ApplicantList applicantList) {
        this.applicantList = applicantList;
   }



   //----Skills----
   public void addSkill(String skill) {
        requiredskills.add(skill);
        applicantList.notifyObservers();
   }

   public void removeSkill(String skill) {
        requiredskills.remove(skill);
        applicantList.notifyObservers();
   }

   //----Strategy----

   public void setStrategy(SelectionStrategy strategy) {
        this.strategy = strategy;
        applicantList.notifyObservers();
   }

   //----Search----

   public void search () {
        applicantList.notifyObservers();
   }

   //----Getters----

   public List<String> getRequiredSkills() {
        return requiredskills;
   }

   public SelectionStrategy getStrategy() {
        return strategy;
   }


   }



