package org.osgifx.core.impl.service;

import javax.persistence.EntityManagerFactory;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.osgifx.core.model.Person;
import org.osgifx.core.service.PersonService;

/**
 *
 * @author filipeportes
 */
@Component
@Provides
@Instantiate
public class PersonServiceImpl extends GenericServiceImpl<Person> implements PersonService{
    
    @Requires
    private EntityManagerFactory entityManagerFactory;

    @Override
    protected EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    
}
