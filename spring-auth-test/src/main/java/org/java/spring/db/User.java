package org.java.spring.db;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int id;
	
	@Column(nullable = false, unique = true)
	@NotBlank(message = "username is mandatory")
	@NotNull(message = "username cannot be null")
	@NotEmpty(message = "usernamae cannot be null")
	@Length(min = 3 ,max = 16, message = "username must be max 16 chars")
	private String username;
	
	@Column(nullable = false)
	@NotBlank(message = "pswd is mandatory")
	@NotNull(message = "pswd cannot be null")
	@NotEmpty(message = "pswd cannot be null")
	@JsonIgnore
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Role> roles;
	

	public User() {}
	
	public User(String username,String password) {
		setUsername(username);
		setPassword(password);
		setRoles(roles);
	}
	
	public User(String username,String password, Role ...roles) {
		setUsername(username);
		setPassword(password);
		setRoles(roles);
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public void setRoles(Role... roles) {
		setRoles(Arrays.asList(roles));
	}
	

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return getRoles().stream()
				.map(r -> new SimpleGrantedAuthority(r.getName()))
			.toList();
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() { return true; }
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() { return true; }
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() { return true; }
	@JsonIgnore
	@Override
	public boolean isEnabled() { return true; }
	
	@Override
	public String toString() {
		return "["+getId()+"]"+getUsername();
	}
}
