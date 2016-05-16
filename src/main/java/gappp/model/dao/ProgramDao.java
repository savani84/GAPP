package gappp.model.dao;

import java.util.List;

import gappp.model.Department;
import gappp.model.Program;

public interface ProgramDao {
    
    List<Program> getProgram();
    
    List<Program> getProgram(String deptName);
    
    Program saveProgram(Program program);
    
    Program getProgram(Integer id);
    
    Program getProgramByName(String programname,String deptName);
    
    void deleteProgram(Integer id);
	
	void updateDepartment(String prgmname, Integer dptID,Integer id);
}