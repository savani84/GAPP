package gappp.model.dao;

import java.util.List;

import gappp.model.Application;
import gappp.model.OtherField;
import gappp.model.OtherFieldValue;

public interface OtherFieldValueDao {
   
	OtherFieldValue saveOtherFieldValue(OtherFieldValue otherFieldValue);
	
	List<OtherFieldValue> getOtherFieldValues(Integer appid);
	
	void updateOtherFileName(Integer otherID,String fileName);
	
	String getFileName(Integer otherValueID);
	
	void removeOtherFieldValue(Integer otherValueID);
}