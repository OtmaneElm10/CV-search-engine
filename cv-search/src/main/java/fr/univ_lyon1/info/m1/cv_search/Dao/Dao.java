package fr.univ_lyon1.info.m1.cv_search.Dao;

import java.io.Serializable;
import java.util.List;

/**
* Dao interface.
* @param <T> the type of the element
* @param <ID> the type of the id (ex: String, Long, etc.)
*/
public interface Dao<T, ID extends Serializable> {
    
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
     * update an element.
     * @param id the id of the element to update
     * @param element the new element
     */
    boolean update(ID id, T element);


    /**
     * delete an element.
     * @param element the id of the element to delete
     */
    void delete(T element);

    
    /**
     * delete an element by id.
     * @param id identifier (can be String, Long, etc.)
     */
    void deleteById(ID id);



    
} 
