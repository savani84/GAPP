package gappp.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "others_field_value")
public class OtherFieldValue implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String otherValue;
	
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Application application;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private OtherField otherField;
	
	public OtherFieldValue()
    {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getOtherValue() {
		return otherValue;
	}

	public void setOtherValue(String otherValue) {
		this.otherValue = otherValue;
	}

	public OtherField getOtherField() {
		return otherField;
	}

	public void setOtherField(OtherField otherField) {
		this.otherField = otherField;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
}
