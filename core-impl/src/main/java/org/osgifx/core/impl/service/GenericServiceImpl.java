package org.osgifx.core.impl.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.osgifx.core.model.Model;
import org.osgifx.core.service.GenericService;

/**
 *
 * @author filipe
 */
public abstract class GenericServiceImpl<T extends Model> implements GenericService<T> {

    private EntityManager em;

    private EntityManagerImpl entityManagerImpl;

    private final Class<T> type;

    protected GenericServiceImpl() {
        this.type = getGenericClass();
    }

    @SuppressWarnings("unchecked")
    private Class<T> getGenericClass() {
        Class<T> result = null;
        Type classType = this.getClass().getGenericSuperclass();

        if (classType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) classType;
            Type[] fieldArgTypes = pt.getActualTypeArguments();
            result = (Class<T>) fieldArgTypes[0];
        }
        return result;
    }
    
    abstract protected EntityManagerFactory getEntityManagerFactory();

    protected EntityManager getEntityManager() {
        if (em == null) {
            em = getEntityManagerFactory().createEntityManager();
        }

        return em;
    }

    protected EntityManagerImpl getEntityManagerImpl() {
        if (entityManagerImpl == null) {
            entityManagerImpl = (EntityManagerImpl) getEntityManager().getDelegate();
        }
        return entityManagerImpl;
    }

    @Override
    public void persist(T entity) {
        try {

            getEntityManager().getTransaction().begin();
            getEntityManager().persist(entity);
            getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void remove(T entity) {
        try {

            getEntityManager().getTransaction().begin();
            getEntityManager().remove(entity);
            getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void merge(T entity) {
        try {

            getEntityManager().getTransaction().begin();
            getEntityManager().merge(entity);
            getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public List<T> findAll() {
        return getEntityManagerImpl().createQuery(getEntityManagerImpl().getCriteriaBuilder().createQuery(type)).getResultList();
    }

    @Override
    public T findById(Long id) {
        return (T) getEntityManagerImpl().find(type.getName(), id);
    }

}
