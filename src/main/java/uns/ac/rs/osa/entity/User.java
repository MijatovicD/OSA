package uns.ac.rs.osa.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="users")
public class User implements Serializable{

	public enum Role{
		ADMIN,
		USER,
		COMMENTATOR
	}

	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="user_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="name", unique=false, nullable=false)
	private String name;
	
	@Column(name="username", unique=false, nullable=false)
	private String username;
	
	@Column(name="password", unique=false, nullable=false)
	private String password;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="user")
	private Set<Post> posts = new HashSet<Post>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="user")
	private Set<Comment> comments = new HashSet<Comment>();

	@Enumerated(EnumType.STRING)
	@Column(name="role", unique=false, nullable=false)
	private Role role;

	public User(Integer id, String name, String username, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public User() {
		
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/*
	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
*/

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", posts=" + posts +
				", comments=" + comments +
				'}';
	}
}
