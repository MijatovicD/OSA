package uns.ac.rs.osa.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;



@Entity
@Table(name="posts")
public class Post implements Serializable{
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="post_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="title", unique=false, nullable=false)
	private String title;
	
	@Column(name="description", unique=false, nullable=false)
	private String description;
	
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    
	@Column(name="date", unique=false, nullable=false)
	private Date date;
	
	@Column(name="likes", unique=false, nullable=false)
	private Integer likes;
	
	@Column(name="dislikes", unique=false, nullable=false)
	private Integer dislikes;
	
	@Column(name="longitude", unique=false, nullable=false)
	private float longitude;
	
	@Column(name="latitude", unique=false, nullable=false)
	private float latitude;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="post")
	private Set<Comment> comments = new HashSet<Comment>();
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id", nullable=false)
	private User user;
	
    @ManyToMany(fetch = LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "post_tags",joinColumns = { @JoinColumn(name = "post_id") },inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tag> tags = new HashSet<Tag>();

	  public void add(Comment comment) {
	    if (comment.getPost() != null)
	      comment.getPost().getComments().remove(comment);
	    comment.setPost(this);
	    getComments().add(comment);
	  }
	
	public Post() {
		
	}
	
	public Post(Integer id, String title, String description, Date date, Integer likes, Integer dislikes,
			float longitude, float latitude, Set<Comment> comments, User user, Set<Tag> tags) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.likes = likes;
		this.dislikes = dislikes;
		this.longitude = longitude;
		this.latitude = latitude;
		this.comments = comments;
		this.user = user;
		this.tags = tags;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getDislikes() {
		return dislikes;
	}

	public void setDislikes(Integer dislikes) {
		this.dislikes = dislikes;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", description=" + description + ", photo="
				+ Arrays.toString(photo) + ", date=" + date + ", likes=" + likes + ", dislikes=" + dislikes
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", comments=" + comments + ", user=" + user
				+ ", tags=" + tags + "]";
	}
	
	
}
