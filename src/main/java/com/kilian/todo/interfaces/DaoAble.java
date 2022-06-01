package com.kilian.todo.interfaces;

import java.util.List;

/**
 * Allow to provides basic operations for a CRUD System and more
 * <ul>
 * <li>Create : #save(T)</li>
 * <li>Read : #find(T), findBy(), findOne() and #findAll()</li>
 * <li>Update : #save(T)</li>
 * <li>Delete : #remove(T)</li>
 * </ul>
 *
 * @author Kilian
 * @param <T> The root entity to search
 * @param <K> the type of the identifier @Id or Composite Key (@Embedable)
 */
public interface DaoAble<T, K> {
    
    /**
     * Find the entity T for the specified identifier id
     *
     * @param id the identifier for the entity
     * @return the entity
     */
    public T find(K id);
	
    /**
     * Find entities T for the specified list of identifiers ids
     *
     * @param ids the list of identifiers for the entity
     * @return the list of entities 
     */
    public List<T> find(K[] ids);
    
    /**
     * Find all entities of T<br>
     * <b>Warning : Be careful of the use of findAll and memory consumption</b>
     *
     * @return the list of entities
     */
    public List<T> findAll();

    /**
     * Save the specified entity T and return the Id
     *
     * @param t the entity to save
     * @return the Id
     */
    public K save(T t);

    /**
     * Delete the specified entity T
     *
     * @param t the entity to remove
     */
    public void remove(T t);

    /**
     * Delete the entity T using its primary key k
     *
     * @param k the primary key
     */
    public void delete(K k);
	
}
