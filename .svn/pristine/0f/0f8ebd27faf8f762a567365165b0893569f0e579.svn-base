package gappp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Department implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "dept_name")
	private String deptName;
	
	@OneToMany(mappedBy="department",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<User> users;
	@OneToMany(mappedBy="department",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<Program> Program;
	@OneToMany(mappedBy="department",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<OtherField> otherfield;
	@OneToMany(mappedBy="department",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<Application> application;
	
	public Department()
    {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Program> getProgram() {
		return Program;
	}

	public void setProgram(List<Program> program) {
		Program = program;
	}

	public List<OtherField> getOtherfield() {
		return otherfield;
	}

	public void setOtherfield(List<OtherField> otherfield) {
		this.otherfield = otherfield;
	}
	
	
}
