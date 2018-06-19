package uns.ac.rs.osa.service;

import javafx.geometry.Pos;
import uns.ac.rs.osa.entity.Post;
import uns.ac.rs.osa.entity.Tag;
import uns.ac.rs.osa.entity.User;

import java.util.Date;
import java.util.List;

public interface PostServiceInterface {

	Post findOne(Integer postId);
	
	List<Post> findAll();
	
	Post save(Post post);
	
	void remove(Integer id);
	
	List<Post> findByTag(Tag tagId);

	List<Post> findByUser(User userId);

	List<Post> findByUsersName(User userName);

	List<Post> findAllByOrderByDateDesc();

	List<Post> findAllOrderByLikesDesc();
}
