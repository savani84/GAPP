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
@Table(name = "programs")
public class Program implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "prog_name")
	private String progName;
	
	@OneToMany(mappedBy="program",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<Application> application;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Department department;
	
	public Program()
    {
    }
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProgName() {
		return progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}

	public List<Application> getApplication() {
		return application;
	}
	public void setApplication(List<Application> application) {
		this.application = application;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
