package fr.univ_lyon1.info.m1.cv_search.Dao;

import java.util.List;

/**
* Dao interface.
* @param <T> the type of the element
*/
public interface Dao<T> {
    
    /**
     * Add an element.
     * @param element the element to add
     */
    void add(T element);
    

    /**
     * find all elements.
     * @return a list of elements
     */
    List<T> findAll();

    /**
     * find an element by id.
     * @param id the id of the element
     * @return the element.
     */
    T findById(int id);
    
    /**
     * update an element.
     * @param id the id of the element to update
     * @param element the new element
     */
    void update(Long id, T element);


    /**
     * delete an element.
     * @param element the id of the element to delete
     */
    void delete(T element);



    
} 
