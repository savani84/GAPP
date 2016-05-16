package gappp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gappp.model.Application;
import gappp.model.Degree;
import gappp.model.Department;
import gappp.model.dao.DegreeDao;

@Repository
public class DegreeDaoImpl implements DegreeDao {

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	@Transactional
	public Degree saveDegree(Degree degree) {
		return entityManager.merge(degree);
	}

	@Override
	public List<Degree> getDegrees(Integer appid) {
		return entityManager.createQuery( "select d from Degree d join d.application a where a.id = :appid", Degree.class )
				.setParameter("appid", appid)
				.getResultList();
	}

	@Override
	@Transactional
	public void updateDegreeFileName(Integer degreeID, String fileName) {
		entityManager.createQuery( "update " + Degree.class.getName() + " set transcript = :trascriptname where id = :id")
								 .setParameter("id", degreeID)
								 .setParameter("trascriptname", fileName)
								 .executeUpdate();
		
	}

	@Override
	@Transactional
	public void updateDegree(String universityName, String degreeName,
			String major, Double gpa, String timePeriod,
			Application application, Integer degreeid) {
		entityManager.createQuery( "update " + Degree.class.getName() + " set universityName = :universityName," + 
				   "degreeName = :degreeName,major = :major,gpa = :gpa,timePeriod = :timePeriod" +
				   " where id = :id")
					 .setParameter("universityName", universityName)
					 .setParameter("degreeName", degreeName)
					 .setParameter("major", major)
					 .setParameter("gpa", gpa)
					 .setParameter("timePeriod", timePeriod)
					 .setParameter("id", degreeid)
					 .executeUpdate();
		
	}

	@Override
	public String getFileName(Integer degreeid) {
		Degree degree = entityManager.createQuery( "from Degree where id = :id", Degree.class )
				.setParameter("id", degreeid)
				.getSingleResult();
		return degree.getTranscript();
	}

	@Override
	@Transactional
	public void removeDegree(Integer degreeid) {
		entityManager.createQuery( "delete " + Degree.class.getName() + " where id = :id")
		 			 .setParameter("id", degreeid).executeUpdate();
	}
}