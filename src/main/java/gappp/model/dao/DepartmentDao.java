package gappp.model.dao;

import java.util.List;

import gappp.model.Department;

public interface DepartmentDao {
    
    List<Department> getDepartment();
    
    Department saveDepartment(Department department);

    Department getDepartment(String deptname);
    
    Department getDepartment(Integer id);
    
    void deleteDepartment(Integer id);
    
    void updateDepartment(String deptname,Integer id);
    
    Integer getDepartmentID(String deptname);
}