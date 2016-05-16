package gappp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "applications")
public class Application implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private Integer cin;
	private String phoneno;
	private String gender;
	private String term;
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth")
	private Date dob;
	@Column(name = "citizen_country")
	private String citizenCountry;
	@Temporal(TemporalType.DATE)
	@Column(name = "application_date")
	private Date applicationDate;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private User users;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Status status;
	@OneToMany(mappedBy="application",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<Comment> comment;
	@OneToMany(mappedBy="application",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<Degree> degree;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Department department;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Program program;
	@OneToMany(mappedBy="application",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<OtherFieldValue> otherFieldValue;
	
	public Application()
    {
		comment = new ArrayList<Comment>();
		degree = new ArrayList<Degree>();
		otherFieldValue = new ArrayList<OtherFieldValue>();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getCin() {
		return cin;
	}

	public void setCin(Integer cin) {
		this.cin = cin;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCitizenCountry() {
		return citizenCountry;
	}

	public void setCitizenCountry(String citizenCountry) {
		this.citizenCountry = citizenCountry;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public List<Degree> getDegree() {
		return degree;
	}

	public void setDegree(List<Degree> degree) {
		this.degree = degree;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public List<OtherFieldValue> getOtherFieldValue() {
		return otherFieldValue;
	}

	public void setOtherFieldValue(List<OtherFieldValue> otherFieldValue) {
		this.otherFieldValue = otherFieldValue;
	}
	
}
