package gappp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gappp.model.Application;
import gappp.model.OtherField;
import gappp.model.OtherFieldValue;
import gappp.model.Program;
import gappp.model.dao.OtherFieldDao;

@Repository
public class OtherFieldDaoImpl implements OtherFieldDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<OtherField> getOtherField()
    {
        return entityManager.createQuery( "from OtherField order by id", OtherField.class )
            .getResultList();
    }
    
	@Override
	@Transactional
	public OtherField saveOtherField(OtherField otherField) {
		return entityManager.merge(otherField);
	}

	@Override
	@Transactional
	public void deleteOtherField(Integer id) {
	    entityManager.createQuery( "delete " + OtherFieldValue.class.getName() + " where otherField.id = :id")
		 			 .setParameter("id", id).executeUpdate();
		entityManager.createQuery( "delete " + OtherField.class.getName() + " where id = :id")
		 			 .setParameter("id", id).executeUpdate();
	}

	@Override
	public OtherField getOtherField(Integer id) {
		return entityManager.createQuery( "from OtherField where id = :id", OtherField.class )
				.setParameter("id", id).getSingleResult();
	}

	@Override
	@Transactional
	public void updateDepartment(String nameField, String typeField,
			Boolean required, Integer departmentID, Integer id) {

		entityManager.createQuery( "update " + OtherField.class.getName() + " set nameField = '" 
				+ nameField + "' , typeField = '" + typeField + "' , required = '" + required 
				+ "' , department.id = " + departmentID + " where id = " + id).executeUpdate();
		
	}

	@Override
	public List<OtherField> getOtherField(String deptName) {
		 return entityManager.createQuery( "select o from OtherField o join o.department d " + 
				 						   "where d.deptName = :deptname", OtherField.class )
				 .setParameter("deptname", deptName)
				 .getResultList();
	}

}