package gappp.model.dao;

import java.util.List;

import gappp.model.Application;

public interface StudentDao {
    
	List<Application> getApplication(String username);

	
}