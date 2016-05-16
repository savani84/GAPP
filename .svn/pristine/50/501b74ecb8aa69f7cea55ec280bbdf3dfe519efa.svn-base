package gappp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gappp.model.Application;
import gappp.model.Degree;
import gappp.model.OtherField;
import gappp.model.OtherFieldValue;
import gappp.model.dao.OtherFieldValueDao;

@Repository
public class OtherFieldValueDaoImpl implements OtherFieldValueDao {

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	@Transactional
	public OtherFieldValue saveOtherFieldValue(OtherFieldValue otherFieldValue) {
		return entityManager.merge(otherFieldValue);
	}

	@Override
	public List<OtherFieldValue> getOtherFieldValues(Integer appid) {
		return entityManager.createQuery( "select o from OtherFieldValue o join o.application a where a.id = :appid", OtherFieldValue.class )
				.setParameter("appid", appid)
				.getResultList();
	}

	@Override
	@Transactional
	public void updateOtherFileName(Integer otherID, String fileName) {
		entityManager.createQuery( "update " + OtherFieldValue.class.getName() + " set otherValue = :othername where id = :id")
								 .setParameter("id", otherID)
								 .setParameter("othername", fileName)
								 .executeUpdate();
	}

	@Override
	public String getFileName(Integer otherValueID) {
		OtherFieldValue otherFieldValue = entityManager.createQuery( "from OtherFieldValue where id = :id", OtherFieldValue.class )
				.setParameter("id", otherValueID)
				.getSingleResult();
		return otherFieldValue.getOtherValue();
	}

	@Override
	@Transactional
	public void removeOtherFieldValue(Integer otherValueID) {
		entityManager.createQuery( "delete " + OtherFieldValue.class.getName() + " where id = :id")
		 			 .setParameter("id", otherValueID).executeUpdate();
	}

}