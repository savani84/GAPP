package gappp.model.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gappp.model.Application;
import gappp.model.Department;
import gappp.model.Program;
import gappp.model.Status;
import gappp.model.User;
import gappp.model.dao.ApplicationDao;

@Repository
public class ApplicationDaoImpl implements ApplicationDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Application> getApplication(String Department,String Term)
    {
    	return entityManager.createQuery( "select a from Application a join a.program p join p.department d" + 
    									  " where term = :term and d.deptName = :DeptName", Application.class )
        	.setParameter("DeptName", Department)
    		.setParameter("term", Term)
            .getResultList();
    }
    
    @Override
	@Transactional
	public Application saveApplication(Application application) {
		return entityManager.merge(application);
	}

	@Override
	public Application getApplication(Integer appID) {
		return entityManager.find( Application.class, appID );
	}

	@Override
	@Transactional
	public void updateApplicationStatus(Integer appID,Status status) {
		entityManager.createQuery( "update " + Application.class.getName() + " set status = :status where id = :id")
		 						 .setParameter("id", appID)
		 						 .setParameter("status", status)
		 						 .executeUpdate();
	}
	@Override
	@Transactional
	public void updateApplicationStatuswithDate(Integer appID, Status status) {
		entityManager.createQuery( "update " + Application.class.getName() + " set status = :status," + 
								   "applicationDate = :applicationDate where id = :id")
					 .setParameter("id", appID)
					 .setParameter("status", status)
					 .setParameter("applicationDate", new Date())
					 .executeUpdate();
		
	}

	@Override
	public List<Application> getApplicationsByEmail(String email) {
		return entityManager.createQuery( "select a from Application a join a.users u" + 
				  " where u.eMail = :email order by a.applicationDate", Application.class )
				.setParameter("email", email)
				.getResultList();
	}

	@Override
	@Transactional
	public void updateApplication(String term, Department department,
			Program program, String firstName, String lastName, Date dob,
			String gender, String citizen, String phoneno, Integer appID) {
		entityManager.createQuery( "update " + Application.class.getName() + " set term = :term," + 
								   "department = :department,program = :program,firstName = :firstName," +
								   "lastName = :lastName,dob = :dob,gender = :gender," +
								   "citizenCountry = :citizen,phoneno = :phoneno" +
								   " where id = :id")
					 .setParameter("term", term)
					 .setParameter("department", department)
					 .setParameter("program", program)
					 .setParameter("firstName", firstName)
					 .setParameter("lastName", lastName)
					 .setParameter("dob", dob)
					 .setParameter("gender", gender)
					 .setParameter("citizen", citizen)
					 .setParameter("phoneno", phoneno)
					 .setParameter("id", appID)
					 .executeUpdate();
	}


	
}