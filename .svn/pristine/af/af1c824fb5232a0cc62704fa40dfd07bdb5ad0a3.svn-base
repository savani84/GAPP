package gappp.model.dao;

import java.util.Date;
import java.util.List;

import gappp.model.Application;
import gappp.model.Department;
import gappp.model.Program;
import gappp.model.Status;

public interface ApplicationDao {
    
	
	List<Application> getApplication(String Department,String Term);
	
	List<Application> getApplicationsByEmail(String email);
	
	Application getApplication(Integer appID);
	
	Application saveApplication(Application application);
	
	void updateApplicationStatus(Integer appID,Status status);
	
	void updateApplicationStatuswithDate(Integer appID,Status status);
	
	void updateApplication(String term,Department department,Program program,String firstName,String lastName,
			Date dob,String gender,String citizen,String phoneno,Integer appID);

}