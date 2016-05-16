package gappp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import gappp.model.Application;
import gappp.model.dao.StudentDao;

@Repository
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Application> getApplication(String email)
    {
    	return entityManager.createQuery( "select a from Application a join a.users u where u.eMail = '" + email + "'", Application.class )
            .getResultList();
    }

}