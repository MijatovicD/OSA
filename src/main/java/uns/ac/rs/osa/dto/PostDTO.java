package uns.ac.rs.osa.dto;

import java.io.Serializable;
import java.util.Date;

import uns.ac.rs.osa.entity.Post;

public class PostDTO implements Serializable{

	private Integer id;
	private String title;
	private String description;
	private byte[] photo;
	private Date date;
	private int likes;
	private int dislikes;
	private float longitude;
	private float latitude;
	private UserDTO user;
	
	public PostDTO() {
		
	}
	
	public PostDTO(Integer id, String title, String description,byte[] photo, Date date, int likes, int dislikes,float longitude, float latitude, UserDTO user) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.photo = photo;
		this.date = date;
		this.likes = likes;
		this.dislikes = dislikes;
		this.longitude = longitude;
		this.latitude = latitude;
		this.user = user;
	}
	
	public PostDTO(Post post) {
		this(post.getId(), post.getTitle(), post.getDescription(), post.getPhoto(), post.getDate(), post.getLikes(), post.getDislikes(), post.getLongitude(), post.getLatitude(), new UserDTO(post.getUser()));
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
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
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

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	
}
