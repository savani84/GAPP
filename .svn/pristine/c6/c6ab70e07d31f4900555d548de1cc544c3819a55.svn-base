package gappp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gappp.model.Application;
import gappp.model.Department;
import gappp.model.OtherField;
import gappp.model.OtherFieldValue;
import gappp.model.Program;
import gappp.model.Role;
import gappp.model.User;
import gappp.model.dao.DepartmentDao;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Department> getDepartment()
    {
        return entityManager.createQuery( "from Department order by id", Department.class )
            .getResultList();
    }

	@Override
	@Transactional
	public Department saveDepartment(Department department) {
		return entityManager.merge(department);
	}

	@Override
	public Department getDepartment(String deptname) {
		return entityManager.createQuery( "from Department where deptName = :deptname", Department.class )
				.setParameter("deptname", deptname).getSingleResult();
	}
	
	@Override
	public Department getDepartment(Integer id) {
		return entityManager.createQuery( "from Department where id = :id", Department.class )
							.setParameter("id", id).getSingleResult();
	}

	@Override
	@Transactional
	public void deleteDepartment(Integer id) {
		entityManager.createQuery( "delete " + Program.class.getName() + " where department.id = :id")
		 			 .setParameter("id", id).executeUpdate();
		entityManager.createQuery( "delete " + OtherField.class.getName() + " where department.id = :id")
		 			 .setParameter("id", id).executeUpdate();
		entityManager.createQuery( "delete " + User.class.getName() + " where department.id = :id")
		 			 .setParameter("id", id).executeUpdate();
		entityManager.createQuery( "delete " + Department.class.getName() + " where id = :id")
		 			 .setParameter("id", id).executeUpdate();
	}

	@Override
	@Transactional
	public void updateDepartment(String deptname, Integer id) {
		entityManager.createQuery( "update " + Department.class.getName() + " set deptName = '" 
						+ deptname + "' where id = " + id).executeUpdate();
	}

	@Override
	public Integer getDepartmentID(String deptname) {
		Department department = entityManager.createQuery( "from Department where deptName = :deptname", Department.class )
				.setParameter("deptname", deptname).getSingleResult();
		return department.getId();
		
	}
}