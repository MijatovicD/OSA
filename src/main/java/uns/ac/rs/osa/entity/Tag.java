package uns.ac.rs.osa.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tags")
public class Tag implements Serializable{

	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="tag_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="name", unique=false, nullable=false)
	private String name;
	

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "tags")
    private Set<Post> posts = new HashSet<Post>();
	
	public Tag() {
		
	}
	
	public Tag(String name, Set<Post> posts) {
		this.name = name;
		this.posts = posts;
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

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", posts=" + posts + "]";
	}
	
	
}
