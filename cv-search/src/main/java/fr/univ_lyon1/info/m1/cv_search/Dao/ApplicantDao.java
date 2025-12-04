
package fr.univ_lyon1.info.m1.cv_search.Dao;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;

import java.util.ArrayList;
import java.util.List;

/**
 * ApplicantDao (used to stock the list of applicants).
 */
public class ApplicantDao implements Dao<Applicant, String> {
    private final List<Applicant> applicants = new ArrayList<>();

    @Override
    public void add(final Applicant applicant) {
        applicants.add(applicant);
    }

    @Override
    public void delete(final Applicant applicant) {
        applicants.remove(applicant);
    }

    /**
     * Delete all elements from the list.
     */
    public void deleteAll() {
        applicants.clear();
    }

    /**
     * Delete an element by id.
     * @param id the id of the element to delete from the list
     */
    @Override
    public void deleteById(final String id) {
        applicants.removeIf(a -> a.getName().equalsIgnoreCase(id)); 
        //removeIf is a method to remove 
        //an element from the list                                                  
    }

    
    @Override
    public List<Applicant> findAll() {
        return new ArrayList<>(applicants);
    }

    /**
     * Find applicant by his name.
     * @param name name of applicant
     * @return the applicant
     */
    public Applicant findByName(final String name) {
        return applicants.stream()
            .filter(a -> a.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);

    }


    /**
     * Find applicant by his skill.
     * @param skill skill of applicant that we are looking for.
     * @return the list of applicants
     */
    public List<Applicant> findBySkill(final String skill) {
        return applicants.stream()
            .filter(a -> a.getSkills().keySet().stream().anyMatch(s -> s.equalsIgnoreCase(skill)))
            .toList();
    }


    /**
     * Update completely an element.
     * if we have to change different fields of an applicant we use this method
     * We can implement later more specific update methods (ex: updateName, updateSkill,etc...)
     * @param id the id of the element to update
     * @param element the new element
     * @return true if the element has been updated, false otherwise
     */
    @Override
    public boolean update(final String id, final Applicant element) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getName().equals(id)) {
                applicants.set(i, element);
                return true;
            }

        }
        return false;
    }


    /**
     * Return the size of the list of applicants.
     * @return
     */
    public int size() {
        return applicants.size();
    }
} 

