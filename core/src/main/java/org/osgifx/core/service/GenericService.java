package org.osgifx.core.service;

import java.util.List;
import org.osgifx.core.model.Model;

public interface GenericService<T extends Model> {
    
    List<T> findAll();
    
    T findById(Long id);

    void persist(T entity);

    void remove(T entity);

    void merge(T entity);

}
