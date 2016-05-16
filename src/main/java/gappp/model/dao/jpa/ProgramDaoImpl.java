package gappp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gappp.model.Application;
import gappp.model.Department;
import gappp.model.Program;
import gappp.model.dao.ProgramDao;

@Repository
public class ProgramDaoImpl implements ProgramDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Program> getProgram()
    {
        return entityManager.createQuery( "from Program order by id", Program.class )
            .getResultList();
    }
    
    @Override
	public Program getProgram(Integer id) {
    	return entityManager.createQuery( "from Program where id = :id", Program.class )
							.setParameter("id", id).getSingleResult();
	}

	@Override
	@Transactional
	public Program saveProgram(Program program) {
		return entityManager.merge(program);
	}

	@Override
	@Transactional
	public void deleteProgram(Integer id) {
		entityManager.createQuery( "update " + Application.class.getName() + " set program = null where program.id = :id")
		 			 .setParameter("id", id).executeUpdate();
		entityManager.createQuery( "delete " + Program.class.getName() + " where id = :id")
		 			 .setParameter("id", id).executeUpdate();
	}

	@Override
	@Transactional
	public void updateDepartment(String prgmname, Integer dptID, Integer id) {
		
		entityManager.createQuery( "update " + Program.class.getName() + " set progName = '" 
				+ prgmname + "' , department.id = " + dptID + " where id = " + id).executeUpdate();
	}

	@Override
	public List<Program> getProgram(String deptName) {
		return entityManager.createQuery( "select p from Program p join p.department d where d.deptName = :DeptName", Program.class )
							.setParameter("DeptName", deptName).getResultList();
	}

	@Override
	public Program getProgramByName(String programname,String deptName) {
		return entityManager.createQuery( "select p from Program p join p.department d " + 
										  "where p.progName = :programname and d.deptName = :DeptName", Program.class )
				.setParameter("programname", programname)
				.setParameter("DeptName", deptName)
				.getSingleResult();
	}
 
	
}