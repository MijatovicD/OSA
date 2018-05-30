package uns.ac.rs.osa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Table(name="comments")
public class Comment implements Serializable{

	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="comments_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="title", unique=false, nullable=false)
	private String title;
	
	@Column(name="description", unique=false, nullable=false)
	private String description;
	
	@Column(name="date", unique=false, nullable=true)
	private Date date;
	
	@Column(name="likes", unique=false, nullable=false)
	private Integer likes;
	
	@Column(name="dislikes", unique=false, nullable=false)
	private Integer dislikes;
	
	@ManyToOne
	@JoinColumn(name="post_id", referencedColumnName="post_id", nullable=false)
	private Post post;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id", nullable=false)
	private User user;
	
	public Comment() {
		
	}
	
	

	public Comment(Integer id, String title, String description, Date date, Integer likes, Integer dislikes, Post post,
			User user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.likes = likes;
		this.dislikes = dislikes;
		this.post = post;
		this.user = user;
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date + ", likes="
				+ likes + ", dislikes=" + dislikes + ", post=" + post + ", user=" + user + "]";
	}


	
	
}
