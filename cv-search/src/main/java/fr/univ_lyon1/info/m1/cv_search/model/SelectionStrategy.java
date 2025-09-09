package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.List;

public interface SelectionStrategy {
    /**
     * Vérifie si un candidat correspond aux compétences demandées.
     *
     * @param applicant le candidat
     * @param skills la liste des compétences recherchées
     * @return true si le candidat est sélectionné selon la stratégie
     */
    boolean isSelected(Applicant applicant, List<String> skills);

    /**
     * Représentation textuelle (affichée dans la ComboBox de la vue).
     */
    @Override
    String toString();
}
