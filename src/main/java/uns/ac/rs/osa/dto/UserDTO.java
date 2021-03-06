package uns.ac.rs.osa.dto;

import java.io.Serializable;

import uns.ac.rs.osa.entity.User;

public class UserDTO implements Serializable {

	private Integer id;
	private String name;
	private String username;
	private String password;
	private User.Role role;
	
	
	public UserDTO() {
		
	}

	public UserDTO(Integer id, String name, String username, String password) {
			this.id = id;
			this.name = name;
			this.username = username;
			this.password = password;
		}
	
	
	public UserDTO(User user) {
		this(user.getId(), user.getName(), user.getUsername(), user.getPassword());
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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

	public User.Role getRole() {
		return role;
	}

	public void setRole(User.Role role) {
		this.role = role;
	}
}
