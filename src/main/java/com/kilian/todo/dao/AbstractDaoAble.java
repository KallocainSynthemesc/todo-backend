package com.kilian.todo.dao;

import com.kilian.todo.dao.jpa.EntityManagerSingleton;
import com.kilian.todo.interfaces.DaoAble;
import com.kilian.todo.interfaces.Identifiable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author Kilian
 * @param <T>
 * @param <K>
 */
public abstract class AbstractDaoAble<T extends Identifiable<K>, K> implements DaoAble<T, K>{

    public abstract Class<T> getEntityClass();
	
    private final EntityManager em = EntityManagerSingleton.getInstance().getEntityManager();
        
    protected abstract SingularAttribute<? super T, K> getSingularAttributePrimaryKey();
    
    @Override
    public T find(K id) {
        return this.getEntityManager().find(getEntityClass(), id);
    }

    @Override
    public List<T> find(K[] ids) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(getEntityClass());
        Root<T> root = criteriaQuery.from(getEntityClass());
        Expression<K> exp = root.get(getSingularAttributePrimaryKey());
        Predicate predicate = exp.in(ids);
        criteriaQuery.select(root).where(predicate);
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(getEntityClass());
        Root<T> root = criteriaQuery.from(getEntityClass());
        criteriaQuery.select(root);
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public K save(T t) {
        try {
            this.getEntityManager().getTransaction().begin();
            this.getEntityManager().persist(t);
            this.getEntityManager().getTransaction().commit();
            return t.getId();
        } catch (Throwable e) {
            if ( this.getEntityManager().getTransaction().isActive() ) {
                this.getEntityManager().getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public void remove(T t) {
        try {
            this.getEntityManager().getTransaction().begin();
            this.getEntityManager().remove(t);
            this.getEntityManager().getTransaction().commit();
        } catch (Throwable e) {
            if ( this.getEntityManager().getTransaction().isActive() ) {
                this.getEntityManager().getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public void delete(K k) {
        try {
            this.getEntityManager().getTransaction().begin();
            T t = this.getEntityManager().find(getEntityClass(), k);
            this.getEntityManager().remove(t);
            this.getEntityManager().getTransaction().commit();
        } catch (Throwable e) {
            if ( this.getEntityManager().getTransaction().isActive() ) {
                this.getEntityManager().getTransaction().rollback();
            }
            throw e;
        }
    }
    
    public EntityManager getEntityManager() {
        return this.em;
    }
    
    public void refreshEntity(T t)
    {
        if(t != null)
        {
            this.getEntityManager().merge(t);
            this.getEntityManager().refresh(t);
        }
    }
    
}
