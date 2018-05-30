package uns.ac.rs.osa.service;

import java.util.List;

import uns.ac.rs.osa.entity.Comment;
import uns.ac.rs.osa.entity.Post;

public interface CommentServiceInterface {
	
	Comment findOne(Integer id);
	
	List<Comment> findAll();
	
	Comment save(Comment comment);
	
	List<Comment> findByPost(Post post);

	List<Comment> findAllByOrderByDateAsc();

	List<Comment> findAllByOrderByLikesAsc();

	void remove(Integer id);
}
