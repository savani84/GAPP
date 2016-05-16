package gappp.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import gappp.model.Role;
import gappp.model.dao.RoleDao;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public Role getRole(String rolename) {
		
		return entityManager.createQuery( "from Role where roleName = :rolename", Role.class )
				.setParameter("rolename", rolename).getSingleResult();
		
	}
}