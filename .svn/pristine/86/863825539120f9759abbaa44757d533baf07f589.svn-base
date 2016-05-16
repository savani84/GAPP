package gappp.model.dao;

import java.util.List;

import gappp.model.Application;
import gappp.model.Degree;

public interface DegreeDao {
   
	Degree saveDegree(Degree degree);
	
	List<Degree> getDegrees(Integer appid);
	
	void updateDegreeFileName(Integer degreeID,String fileName);
	
	void updateDegree(String universityName,String degreeName,String major,Double gpa,
			String timePeriod,Application application,Integer degreeid);

	String getFileName(Integer degreeid);
	
	void removeDegree(Integer degreeid);
}