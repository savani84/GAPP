package gappp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "others_field")
public class OtherField implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "name_field")
	private String nameField;
	@Column(name = "type_field")
	private String typeField;
	private Boolean required;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Department department;
	@OneToMany(mappedBy="otherField",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<OtherFieldValue> otherFieldValue;
	
	public OtherField()
    {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameField() {
		return nameField;
	}

	public void setNameField(String nameField) {
		this.nameField = nameField;
	}

	public String getTypeField() {
		return typeField;
	}

	public void setTypeField(String typeField) {
		this.typeField = typeField;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}


	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<OtherFieldValue> getOtherFieldValue() {
		return otherFieldValue;
	}

	public void setOtherFieldValue(List<OtherFieldValue> otherFieldValue) {
		this.otherFieldValue = otherFieldValue;
	}
	
}
