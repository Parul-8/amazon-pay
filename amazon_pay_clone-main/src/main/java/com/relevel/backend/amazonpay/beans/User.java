package com.relevel.backend.amazonpay.beans;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="user")
public class User  {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userID;
	
	@NotEmpty //checks for null and blank values
	@Pattern(regexp= "^[a-zA-Z ]*$" )
	@Size(min=2, max=20, message="username must have atleast 2 and atmost 20 characters")
	private String username;
	
	@NotNull // checks for null value
	//@Pattern(regexp= "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	@Email
	private String email;
	
	@NotNull
	/*
	 * @Pattern(regexp= "^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])" +
	 * "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,20}$",
	 * message="Password must have 8 characters with atleast one special character")
	 */
	private String password;
	
	//@Size(min=10,max=10)
	//@Pattern(regexp="^[0-9]+$")
	@Pattern(regexp = "^\\d{10}" , message="phone number must have exact 10 digits")
	private String  phoneNumber;
	
	//@NotBlank //check that the specified argument character sequence is not null , a length of zero, empty, or whitespace.
	//@Pattern(regexp = "^(19|[2-9]\\d)$")
	@Min(19)
	@Max(99)
	private Integer age;
	
	@Pattern(regexp=  "male|female" )
	private String gender;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="user_role", joinColumns=@JoinColumn(name="userID"), inverseJoinColumns=@JoinColumn(name="roleId"))
	private Set<Role> roles;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(Long userID, String username, String email, String password, String phoneNumber, Integer age,
			String gender, Set<Role> roles) {
		super();
		this.userID = userID;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.gender = gender;
		this.roles = roles;
	}


	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	

	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", email=" + email + ", password=" + password
				+ "]";
	}

	
	

}
