package gappp.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gappp.model.Program;
import gappp.model.Status;
import gappp.model.dao.StatusDao;

@Repository
public class StatusDaoImpl implements StatusDao {

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public Status getStatusByName(String status) {
		return entityManager.createQuery( "from Status where status = :status", Status.class )
				.setParameter("status", status).getSingleResult();
	}
}